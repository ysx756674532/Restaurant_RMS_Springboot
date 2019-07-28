
function query_Infos(pn) {

    $.ajax({
        type:'GET',
        url:'/getRestCommentCust',
        data:{pn:pn,"restId":rest_id},
        success:function (result) {
            //1、解析并显示数据
            show_table_RestCommentInfos(result);
            //2、解析并显示分页信息
            show_pageInfo(result);
            //3、解析并显示分页条
            show_pageNav(result);
        }
    });
}


//表格数据显示
function show_table_RestCommentInfos(result) {
    $("#table tbody").empty();
    //对result里面的list进行遍历
    $.each(result.list,function (index,restCMInfo) {
        //addClass添加样式
        //构建表格元素
        var countryID = $("<td></td>").append(restCMInfo.custName);
        var countryName = $("<td></td>").append(restCMInfo.commentType);
        var countryCode = $("<td></td>").append(restCMInfo.commentInfo);
        var countryCode1 = $("<td></td>").append(restCMInfo.commentTime);
        //append 方法执行完成以后还是返回原来的元素,appendTo添加到某个元素
        $("<tr></tr>").append(countryID).append(countryName).append(countryCode).append(countryCode1)
            .appendTo($("#table tbody"));
    });
}


