package wolkenag.frontcontroller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class BlogController implements Controller {
	private static final String BLOG_JSP = "/WEB-INF/pages/main/blog.jsp";

	@Override
	public ViewResolver resolve(final HttpServletRequest request, final HttpServletResponse response) {
		ViewResolver resolver = new ViewResolver();

		request.setAttribute("blog_post", "Blog Post");
		resolver.forward(BLOG_JSP);
		return resolver;
	}

}
