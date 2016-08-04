package ch.levkev.omeganote.modelling;

public class MathJaxInjector {
	public String inject(String html) {
		StringBuilder builder = new StringBuilder();
		builder.append("<HTML>\n");
		builder.append("<HEAD>\n");
		builder.append("<script type=\"text/javascript\" async\n");
		// TODO: create local mathjax copy
		builder.append("src=\"https://cdn.mathjax.org/mathjax/latest/MathJax.js?config=TeX-MML-AM_CHTML\">\n");
		builder.append("</script>\n");
		builder.append("<BODY>\n");
		builder.append(html);
		builder.append("</BODY>\n");
		builder.append("</HTML>");
		return builder.toString();
	}
}
