<#macro recurse_macro node parent depth number>
    ${number.increment()}
    <h${depth+1} id="package_${node.name}">
    ${number.toString()} ${node.name}</h${depth+1}>
    <#if node.usecases?has_content>
        ${number.levelDown()}
        <#list node.usecases as usecase>
            ${number.increment()}
            <p id="usecase_${usecase.id}">${number.toString()} UC id: ${usecase.id} <b>${usecase.name}</b></p>
            <p style="margin-left:20px">${usecase.summary}</p>
            <#if usecase.tests?has_content>
                <p><b>Tests:</b></p>
                <#list usecase.tests as test>
                    <a href="#test_${test.id}"><p style="margin-left:20px">id: ${test.id} ${test.name}</p></a>
                </#list>
            </#if>
        </#list>
        ${number.levelUp()}
    </#if>
    <#if node.subpackages?has_content>
        ${number.levelDown()}
    <#elseif (parent.subpackages?size == number.getNumber())>
        ${number.levelUp()}
    </#if>
    <#list node.getSubpackages() as subPackage>
        <@recurse_macro node=subPackage parent=node depth=depth+1 number=number/>
    </#list>
</#macro>

<#macro draw_screens node number>
    <#list node.getScreens() as screen>
        <p><b>Screen nr ${number.getNumber()}</b></p> ${number.increment()}
        <p><img src="data:image/png;base64,${base64.encodeBase64String(screen.data)}" alt="screen_id_${screen.id}"/></p>
        <p><b>Assigned usecases:</b></p>
        <#list screen.getUsecases() as usecase>
            <a href="#usecase_${usecase.id}"><p style="margin-left:20px">UC Id: ${usecase.id} <b>${usecase.name}</b></p></a>
        </#list>
        <br/>
    </#list>
    <#list node.getSubpackages() as subPackage>
        <@draw_screens node=subPackage number=number/>
    </#list>
</#macro>

<#macro list_tests node parent depth number>
    ${number.increment()}
    <h${depth+1} id="package_${node.name}">
    ${number.toString()} ${node.name}</h${depth+1}>
    <#if node.tests?has_content>
        ${number.levelDown()}
        <#list node.tests as test>
            ${number.increment()}
            <p id="test_${test.id}">${number.toString()} Test id: ${test.id} <b>${test.name}</b></p>
            <#if test.summary??>
                <p style="margin-left:20px">${test.summary}</p>
            </#if>
            <#if test.usecases?has_content>
                <p><b>Usecases:</b></p>
                <#list test.usecases as usecase>
                    <a href="#usecase_${usecase.id}"><p style="margin-left:20px">UC id: ${usecase.id} <b>${usecase.name}</b></p></a>
                </#list>
            </#if>
        </#list>
        ${number.levelUp()}
    </#if>
    <#if node.subpackages?has_content>
        ${number.levelDown()}
    <#elseif (parent.subpackages?size == number.getNumber())>
        ${number.levelUp()}
    </#if>
    <#list node.getSubpackages() as subPackage>
        <@list_tests node=subPackage parent=node depth=depth+1 number=number/>
    </#list>
</#macro>


<html>
<title>Project: "${package.project.name}" documentation</title>
<body>
<center><h1>Project: "${package.project.name}" documentation</h1></center>
<h1>Usecases tree</h1></br>
${numbering.startFromZero()}
<@recurse_macro node=package parent=package depth=0 number=numbering/>
<br/><br/>

<h1>Screens</h1>
<@draw_screens node=package number=numbering/>

<h1>Tests</h1></br>
${numbering.startFromZero()}
<@list_tests node=package parent=package depth=0 number=numbering/>
</body>
</html>