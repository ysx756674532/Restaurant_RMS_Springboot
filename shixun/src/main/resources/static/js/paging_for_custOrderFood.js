var orderId;
//根据传入的页码号，进行查询

function setOrderId(id) {
    orderId=id;
}
function query_Infos(pn) {
    $.ajax({
        type:'GET',
        url:'/getOrderFoodInfos',
        data:{pn:pn,"orderId":orderId},
        success:function (result) {
            //1、解析并显示数据
            show_table_OrderFoodInfos(result);
            //2、解析并显示分页信息
            show_pageInfo(result,orderId);
            //3、解析并显示分页条
            show_pageNav(result,orderId);
        }
    });
}


//表格数据显示
function show_table_OrderFoodInfos(result) {
    $("#table tbody").empty();
    //对result里面的list进行遍历
    $.each(result.list,function (index,orderFoodInfo) {
        //addClass添加样式
        //构建表格元素
        var countryID = $("<td></td>").append(orderFoodInfo.foodName);
        var countryName = $("<td></td>").append(orderFoodInfo.foodPrice);
        var countryCode = $("<td></td>").append(orderFoodInfo.remark);
        //append 方法执行完成以后还是返回原来的元素,appendTo添加到某个元素
        $("<tr></tr>").append(countryID).append(countryName).append(countryCode)
            .appendTo($("#table tbody"));
    });
}