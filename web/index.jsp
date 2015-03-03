<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>



<tiles:insertDefinition name="bodyTemplate">
    <tiles:putAttribute name="right">
        Derecha
    </tiles:putAttribute>
    <tiles:putAttribute name="left">
        <a href="<c:url value="/views/bootstrap.jsp"/>">Bootstrap</a>
    </tiles:putAttribute>
</tiles:insertDefinition>
