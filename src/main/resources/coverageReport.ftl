<html>
<title>Project: "${project.name}" Coverage Report</title>
<body>
<center><h1>Project: "${project.name}" Coverage Report</h1></center>
<br/>
<h2>Usecases coverage</h2>
<table border="1">
    <tr>
        <th>Usecase name</th>
        <th>Assigned tests</th>
        <th>Assigned screens</th>
        <th>Summary length</th>
    </tr>
    <#list usecasesInfo as usecase>
        <tr>
            <td>
                ${usecase.name}
            </td>
            <td>
                <#if usecase.testsNumber == 0>
                    <font color="red">${usecase.testsNumber}</font>
                <#else>
                    ${usecase.testsNumber}
                </#if>
            </td>
            <td>
                <#if usecase.screensNumber == 0>
                    <font color="red">${usecase.screensNumber}</font>
                <#else>
                    ${usecase.screensNumber}
                </#if>
            </td>
            <td>
                <#if usecase.summaryLength == 0>
                    <font color="red">${usecase.summaryLength}</font>
                <#else>
                    ${usecase.summaryLength}
                </#if>
            </td>
        </tr>
    </#list>
</table>
<br/>
<h2>Completeness of tests documentation</h2>
<table border="1">
    <tr>
        <th>Test name</th>
        <th>Assigned usecases</th>
        <th>Summary length</th>
    </tr>
    <#list testsInfo as test>
        <tr>
            <td>
                ${test.name}
            </td>
            <td>
                <#if test.usecasesNumber == 0>
                    <font color="red">${test.usecasesNumber}</font>
                <#else>
                    ${test.usecasesNumber}
                </#if>
            </td>
            <td>
                <#if test.summaryLength == 0>
                    <font color="red">${test.summaryLength}</font>
                <#else>
                    ${test.summaryLength}
                </#if>
            </td>
        </tr>
    </#list>
</table>
<br/>
<h2>Unassigned objects</h2>
<table border="1">
    <tr>
        <th>Name</th>
        <th>Type</th>
    </tr>
    <#list unassignedObjects as object>
        <tr>
            <td>
                ${object.name}
            </td>
            <td>
                ${object.type}
            </td>
        </tr>
    </#list>
</table>
<br/>
<h2>Empty pakages</h2>
<table border="1">
    <tr>
        <th>Name</th>
    </tr>
    <#list emptyPackages as package>
        <tr>
            <td>
                ${package.name}
            </td
        </tr>
    </#list>
</table>
</body>
</html>