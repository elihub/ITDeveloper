<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="row">
    <div class="col-md-2">
        <tiles:insertAttribute name="right" />
    </div>
    <div class="col-md-10">
        <tiles:insertAttribute name="left" />
        </div>
</div>
