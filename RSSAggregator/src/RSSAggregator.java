import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.xmltree.XMLTree;
import components.xmltree.XMLTree1;

/**
 * Program to convert an XML RSS (version 2.0) feed from a given URL into the
 * corresponding HTML output file.
 *
 * @author Austin Bolomey.1
 *
 */
public final class RSSAggregator {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private RSSAggregator() {
    }

    /**
     * Outputs the "opening" tags in the generated HTML file. These are the
     * expected elements generated by this method:
     *
     * <html> <head> <title>the channel tag title as the page title</title>
     * </head> <body>
     * <h1>the page title inside a link to the <channel> link</h1>
     * <p>
     * the channel description
     * </p>
     * <table border="1">
     * <tr>
     * <th>Date</th>
     * <th>Source</th>
     * <th>News</th>
     * </tr>
     *
     * @param channel
     *            the channel element XMLTree
     * @param out
     *            the output stream
     * @updates out.content
     * @requires [the root of channel is a <channel> tag] and out.is_open
     * @ensures out.content = #out.content * [the HTML "opening" tags]
     */
    private static void outputHeader(XMLTree channel, SimpleWriter out) {

        assert channel != null : "Violation of: channel is not null";
        assert out != null : "Violation of: out is not null";
        assert channel.isTag() && channel.label().equals("channel") : ""
                + "Violation of: the label root of channel is a <channel> tag";
        assert out.isOpen() : "Violation of: out.is_open";

        String title = "";
        String description = "";
        String link = "";

        int titleNode = getChildElement(channel, "title");
        int descriptionNode = getChildElement(channel, "description");
        int linkNode = getChildElement(channel, "link");

        //Ensures tag exists and that it has children to verify requires/ensures clause
        if (titleNode >= 0 && channel.child(titleNode).isTag()) {
            if (channel.child(titleNode).numberOfChildren() > 0) {
                title = channel.child(titleNode).child(0).label();
            }
        }

        //Ensures tag exists and that it has children to verify requires/ensures clause
        if (descriptionNode >= 0 && channel.child(descriptionNode).isTag()) {
            if (channel.child(descriptionNode).numberOfChildren() > 0) {
                description = channel.child(descriptionNode).child(0).label();
            }
        }

        //Ensures tag exists and that it has children to verify requires/ensures clause
        if (linkNode >= 0 && channel.child(linkNode).isTag()) {
            if (channel.child(linkNode).numberOfChildren() > 0) {
                link = channel.child(linkNode).child(0).label();
            }
        }

        //Create the header and table for the html page
        out.println("<html><head><title>" + title + "</title></head><body>");
        out.println("<h1><a href= \"" + link + "\">" + title + "</a></h1>");
        out.println("<p>");
        out.println(description);
        out.println("</p>");
        out.println("<table border=\"1\">");
        out.println("<tr>");
        out.println("<th>Date</th>");
        out.println("<th>Source</th>");
        out.println("<th>News</th>");
        out.println("</tr>");
    }

    /**
     * Outputs the "closing" tags in the generated HTML file. These are the
     * expected elements generated by this method:
     *
     * </table>
     * </body> </html>
     *
     * @param out
     *            the output stream
     * @updates out.contents
     * @requires out.is_open
     * @ensures out.content = #out.content * [the HTML "closing" tags]
     */
    private static void outputFooter(SimpleWriter out) {
        assert out != null : "Violation of: out is not null";
        assert out.isOpen() : "Violation of: out.is_open";

        out.println("</table>");
        out.println("</body> </html>");
    }

    /**
     * Finds the first occurrence of the given tag among the children of the
     * given {@code XMLTree} and return its index; returns -1 if not found.
     *
     * @param xml
     *            the {@code XMLTree} to search
     * @param tag
     *            the tag to look for
     * @return the index of the first child of type tag of the {@code XMLTree}
     *         or -1 if not found
     * @requires [the label of the root of xml is a tag]
     * @ensures <pre>
     * getChildElement =
     *  [the index of the first child of type tag of the {@code XMLTree} or
     *   -1 if not found]
     * </pre>
     */
    private static int getChildElement(XMLTree xml, String tag) {
        assert xml != null : "Violation of: xml is not null";
        assert tag != null : "Violation of: tag is not null";
        assert xml.isTag() : "Violation of: the label root of xml is a tag";

        //node must be -1 based on @return tag
        int i = 0, children = xml.numberOfChildren(), node = -1;

        //loop through children of tag until label string matches searched string
        while (node < 0 && i < children) {
            if (xml.child(i).label().equals(tag)) {
                node = i;
            }
            i++;
        }
        return node;
    }

    /**
     * Processes one news item and outputs one table row. The row contains three
     * elements: the publication date, the source, and the title (or
     * description) of the item.
     *
     * @param item
     *            the news item
     * @param out
     *            the output stream
     * @updates out.content
     * @requires [the label of the root of item is an <item> tag] and
     *           out.is_open
     * @ensures <pre>
     * out.content = #out.content *
     *   [an HTML table row with publication date, source, and title of news item]
     * </pre>
     */
    private static void processItem(XMLTree item, SimpleWriter out) {
        assert item != null : "Violation of: item is not null";
        assert out != null : "Violation of: out is not null";
        assert item.isTag() && item.label().equals("item") : ""
                + "Violation of: the label root of item is an <item> tag";
        assert out.isOpen() : "Violation of: out.is_open";

        int children = item.numberOfChildren();
        //initialize the nodes to -1
        int titleNode = -1, dateNode = -1, linkNode = -1, sourceNode = -1;
        int i = 0;

        while (i < children) {
            if (item.child(i).label().equals("title")) {
                titleNode = i;
            }
            if (item.child(i).label().equals("link")) {
                linkNode = i;
            }
            if (item.child(i).label().equals("pubDate")) {
                dateNode = i;
            }
            if (item.child(i).label().equals("source")) {
                sourceNode = i;
            }
            i++;
        }
        String title = "", source = "", date = "", url = "", link = "";

        //gets the title label based on the node found
        if (titleNode != -1) {
            title = item.child(titleNode).child(0).label();
        } else {
            title = "No title available";
        }
        if (sourceNode != -1) {
            source = item.child(sourceNode).child(0).label();
            if (item.child(sourceNode).hasAttribute("url")) {
                url = item.child(sourceNode).attributeValue("url");
            }
        } else {
            source = "No source available";
        }
        if (dateNode != -1) {
            date = item.child(dateNode).child(0).label();
        } else {
            date = "No date available";
        }
        if (linkNode >= 0) {
            link = item.child(linkNode).child(0).label();
        }

        //Start html tags
        out.println("<tr>");
        out.println("<th>" + date + "</th>");
        //if no source exists print "no sources..."
        if (url.equals("")) {
            out.println("<th>" + source + "</th>");
        } else {
            out.println("<th><a href= \"" + url + "\">" + source + "</a></th>");
        }
        out.println("<th><a href= \"" + link + "\">" + title + "</a></th>");
        out.println("</tr>");
    }

    /**
     * Processes one XML RSS (version 2.0) feed from a given URL converting it
     * into the corresponding HTML output file.
     *
     * @param url
     *            the URL of the RSS feed
     * @param file
     *            the name of the HTML output file
     * @param out
     *            the output stream to report progress or errors
     * @updates out.content
     * @requires out.is_open
     * @ensures <pre>
     * [reads RSS feed from url, saves HTML document with table of news items
     *   to file, appends to out.content any needed messages]
     * </pre>
     */
    private static void processFeed(String url, String file, SimpleWriter out) {

        //create new xml tree for the feed
        XMLTree list = new XMLTree1(url);
        String title = "", urlchild = "", namechild = "", filechild = "";
        if (list.isTag() && list.hasAttribute("title")) {
            title = list.attributeValue("title");
        }
        out.print("<html>\n<head>\n<title>");
        out.print(title);
        out.println("</title></head>");
        out.println("<body>");
        out.print("<h2>");
        out.print(title);
        out.print("</h2>");
        out.print("<u1>");

        if (list.numberOfChildren() > 0) {
            for (int i = 0; i < list.numberOfChildren(); i++) {
                if (list.child(i).isTag()) {
                    if (list.child(i).hasAttribute("url")) {
                        urlchild = list.child(i).attributeValue("url");
                    }
                    if (list.child(i).hasAttribute("name")) {
                        namechild = list.child(i).attributeValue("name");
                    }
                    if (list.child(i).hasAttribute("file")) {
                        filechild = list.child(i).attributeValue("file");
                    }
                }

                //create the html page for each link
                SimpleWriter out1 = new SimpleWriter1L(filechild);
                XMLTree xml = new XMLTree1(urlchild);
                boolean state = false;

                // Checks that RSS is version 2.0
                if (xml.hasAttribute("version")) {
                    if (xml.attributeValue("version").equals("2.0")) {
                        state = true;

                    } else {

                    }
                }
                //if rss 2.0
                if (state) {
                    int channelPosition = 0;
                    XMLTree channel = null;

                    if (xml.isTag()) {
                        channelPosition = getChildElement(xml, "channel");

                        channel = xml.child(channelPosition);
                        outputHeader(channel, out1);

                        for (int j = 0; j < channel.numberOfChildren(); j++) {
                            if (channel.child(j).label().equals("item")) {
                                processItem(channel.child(j), out1);
                            }
                        }
                    }
                    outputFooter(out1);

                    //create links to each html file
                    out.print("<li><a href=\"" + filechild + "\" >" + namechild
                            + "</a></li>");
                    out.println("</ul>\n" + "  </body>\n" + "</html>");
                } else {
                    out.println("invalid RSS feed");
                }
            }

        }
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments; unused here
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();
        //output html file

        out.print("Enter the URL of an RSS 2.0 news feed: ");
        String input = in.nextLine();

        out.print("Enter name of HTML file with .html extension: ");
        String htmlname = in.nextLine();
        SimpleWriter outputHTML = new SimpleWriter1L(htmlname);

        processFeed(input, htmlname, outputHTML);

        in.close();
        out.close();
        outputHTML.close();
    }

}
