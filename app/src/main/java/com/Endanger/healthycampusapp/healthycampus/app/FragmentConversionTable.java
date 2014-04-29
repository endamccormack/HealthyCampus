package com.Endanger.healthycampusapp.healthycampus.app;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

/**
 * Created by endamccormack on 19/04/2014.
 */
public class FragmentConversionTable extends Fragment {
    String tableHTML = "" +
            "<style>" +
            ".CSSTableGenerator {\n" +
            "\tfont-size:24px;\n" +
            "\tmargin:0px;padding:0px;\n" +
            "\twidth:100%;\n" +
            "\tbox-shadow: 10px 10px 5px #888888;\n" +
            "\tborder:1px solid #4c4c4c;\n" +
            "\t\n" +
            "\t-moz-border-radius-bottomleft:0px;\n" +
            "\t-webkit-border-bottom-left-radius:0px;\n" +
            "\tborder-bottom-left-radius:0px;\n" +
            "\t\n" +
            "\t-moz-border-radius-bottomright:0px;\n" +
            "\t-webkit-border-bottom-right-radius:0px;\n" +
            "\tborder-bottom-right-radius:0px;\n" +
            "\t\n" +
            "\t-moz-border-radius-topright:0px;\n" +
            "\t-webkit-border-top-right-radius:0px;\n" +
            "\tborder-top-right-radius:0px;\n" +
            "\t\n" +
            "\t-moz-border-radius-topleft:0px;\n" +
            "\t-webkit-border-top-left-radius:0px;\n" +
            "\tborder-top-left-radius:0px;\n" +
            "}.CSSTableGenerator table{\n" +
            "    border-collapse: collapse;\n" +
            "        border-spacing: 0;\n" +
            "\twidth:100%;\n" +
            "\theight:100%;\n" +
            "\tmargin:0px;padding:0px;\n" +
            "}.CSSTableGenerator tr:last-child td:last-child {\n" +
            "\t-moz-border-radius-bottomright:0px;\n" +
            "\t-webkit-border-bottom-right-radius:0px;\n" +
            "\tborder-bottom-right-radius:0px;\n" +
            "}\n" +
            ".CSSTableGenerator table tr:first-child td:first-child {\n" +
            "\t-moz-border-radius-topleft:0px;\n" +
            "\t-webkit-border-top-left-radius:0px;\n" +
            "\tborder-top-left-radius:0px;\n" +
            "}\n" +
            ".CSSTableGenerator table tr:first-child td:last-child {\n" +
            "\t-moz-border-radius-topright:0px;\n" +
            "\t-webkit-border-top-right-radius:0px;\n" +
            "\tborder-top-right-radius:0px;\n" +
            "}.CSSTableGenerator tr:last-child td:first-child{\n" +
            "\t-moz-border-radius-bottomleft:0px;\n" +
            "\t-webkit-border-bottom-left-radius:0px;\n" +
            "\tborder-bottom-left-radius:0px;\n" +
            "}.CSSTableGenerator tr:hover td{\n" +
            "\t\n" +
            "}\n" +
            ".CSSTableGenerator tr:nth-child(odd){ background-color:#ffffff; }\n" +
            ".CSSTableGenerator tr:nth-child(even)    { background-color:#cccccc; }.CSSTableGenerator td{\n" +
            "\tvertical-align:middle;\n" +
            "\t\n" +
            "\t\n" +
            "\tborder:1px solid #4c4c4c;\n" +
            "\tborder-width:0px 1px 1px 0px;\n" +
            "\ttext-align:left;\n" +
            "\tpadding:7px;\n" +
            "\tfont-size:24px;\n" +
            "\tfont-family:Arial;\n" +
            "\tfont-weight:normal;\n" +
            "\tcolor:#000000;\n" +
            "}.CSSTableGenerator tr:last-child td{\n" +
            "\tborder-width:0px 1px 0px 0px;\n" +
            "}.CSSTableGenerator tr td:last-child{\n" +
            "\tborder-width:0px 0px 1px 0px;\n" +
            "}.CSSTableGenerator tr:last-child td:last-child{\n" +
            "\tborder-width:0px 0px 0px 0px;\n" +
            "}\n" +
            ".CSSTableGenerator tr:first-child td{\n" +
            "\t\tbackground:-o-linear-gradient(bottom, #cccccc 5%, #7f7f7f 100%);\tbackground:-webkit-gradient( linear, left top, left bottom, color-stop(0.05, #cccccc), color-stop(1, #7f7f7f) );\n" +
            "\tbackground:-moz-linear-gradient( center top, #cccccc 5%, #7f7f7f 100% );\n" +
            "\tfilter:progid:DXImageTransform.Microsoft.gradient(startColorstr=\"#cccccc\", endColorstr=\"#7f7f7f\");\tbackground: -o-linear-gradient(top,#cccccc,7f7f7f);\n" +
            "\n" +
            "\tbackground-color:#cccccc;\n" +
            "\tborder:0px solid #4c4c4c;\n" +
            "\ttext-align:center;\n" +
            "\tborder-width:0px 0px 1px 1px;\n" +
            "\tfont-size:18px;\n" +
            "\tfont-family:Arial;\n" +
            "\tfont-weight:bold;\n" +
            "\tcolor:#ffffff;\n" +
            "}\n" +
            ".CSSTableGenerator tr:first-child:hover td{\n" +
            "\tbackground:-o-linear-gradient(bottom, #cccccc 5%, #7f7f7f 100%);\tbackground:-webkit-gradient( linear, left top, left bottom, color-stop(0.05, #cccccc), color-stop(1, #7f7f7f) );\n" +
            "\tbackground:-moz-linear-gradient( center top, #cccccc 5%, #7f7f7f 100% );\n" +
            "\tfilter:progid:DXImageTransform.Microsoft.gradient(startColorstr=\"#cccccc\", endColorstr=\"#7f7f7f\");\tbackground: -o-linear-gradient(top,#cccccc,7f7f7f);\n" +
            "\n" +
            "\tbackground-color:#cccccc;\n" +
            "}\n" +
            ".CSSTableGenerator tr:first-child td:first-child{\n" +
            "\tborder-width:0px 0px 1px 0px;\n" +
            "}\n" +
            ".CSSTableGenerator tr:first-child td:last-child{\n" +
            "\tborder-width:0px 0px 1px 1px;\n" +
            "}" +
            "</style>" +
            "<div class=\"CSSTableGenerator\" >" +
            "<table border=\"1\" style=\"width:100%; height:100%;\">\n" +
            "                    <tbody>\n" +
            "                        <tr>\n" +
            "                            <td colspan=\"2\" align=\"center\" valign=\"top\">Capacity</th>\n" +
            "                            <td colspan=\"2\" align=\"center\" valign=\"top\">Weight</th>\n" +
            "                        </tr>\n" +
            "                        <tr>\n" +
            "                            <td align=\"left\" valign=\"top\">1 milliliter </td>\n" +
            "                            <td align=\"left\" valign=\"top\"><sup>1</sup>/5 teaspoon</td>\n" +
            "                            <td align=\"left\" valign=\"top\">1 gram </td>\n" +
            "                            <td align=\"left\" valign=\"top\">.035 ounce</td>\n" +
            "                        </tr>\n" +
            "                        <tr>\n" +
            "                            <td align=\"left\" valign=\"top\">5 ml </td>\n" +
            "                            <td align=\"left\" valign=\"top\">1 teaspoon</td>\n" +
            "                            <td align=\"left\" valign=\"top\">100 grams </td>\n" +
            "                            <td align=\"left\" valign=\"top\">3.5 ounces</td>\n" +
            "                        </tr>\n" +
            "                        <tr>\n" +
            "                            <td align=\"left\" valign=\"top\">15 ml </td>\n" +
            "                            <td align=\"left\" valign=\"top\">1 tablespoon</td>\n" +
            "                            <td align=\"left\" valign=\"top\">500 grams</td>\n" +
            "                            <td align=\"left\" valign=\"top\">1.10 pounds</td>\n" +
            "                        </tr>\n" +
            "                        <tr>\n" +
            "                            <td align=\"left\" valign=\"top\">100 ml </td>\n" +
            "                            <td align=\"left\" valign=\"top\">3.4 fluid oz</td>\n" +
            "                            <td align=\"left\" valign=\"top\">1 kilogram </td>\n" +
            "                            <td align=\"left\" valign=\"top\">2.205 pounds<br>=&nbsp;35 ounces</td>\n" +
            "                        </tr>\n" +
            "                        <tr>\n" +
            "                            <td align=\"left\" valign=\"top\">240 ml </td>\n" +
            "                            <td align=\"left\" valign=\"top\">1 cup</td>\n" +
            "                            <td align=\"right\" valign=\"top\">&nbsp;</td>\n" +
            "                            <td align=\"left\" valign=\"top\">&nbsp;</td>\n" +
            "                        </tr>\n" +
            "                        <tr>\n" +
            "                            <td align=\"left\" valign=\"top\">1 liter</td>\n" +
            "                            <td align=\"left\" valign=\"top\">34 fluid oz<br>=&nbsp;4.2 cups<br>=&nbsp;2.1 pints<br>=&nbsp;1.06 quarts<br>=&nbsp;0.26 gallon<br></td>\n" +
            "                            <td align=\"left\" valign=\"top\">&nbsp;</td>\n" +
            "                            <td align=\"left\" valign=\"top\">&nbsp;</td>\n" +
            "                        </tr>\n" +
            "                    </tbody>\n" +
            "                </table>" +
            "</div>";

    public FragmentConversionTable() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_measurement_converter, container, false);
        WebView webview = (WebView)view.findViewById(R.id.webConversionTable);
        webview.loadData(tableHTML, "text/html", null);
        return view;
    }
}
