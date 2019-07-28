
var time=0;
var videoStream;
var usertype = document.getElementById("userType").innerHTML;

function show(none) {
    if(!none)
    {
        document.getElementById("videoC").style.display="none";
        document.getElementById("imgBlog").style.display="block";
    }
    else {
        document.getElementById("videoC").style.display="block";
        document.getElementById("imgBlog").style.display="none";
    }
}

function sLogin()
{
    $('#my-alert').modal();
    show(false);
    $("#tishi").text("");
}

function exit()
{
	///关闭摄像头资源
	if(videoStream!=null)
	{
		videoStream.stop();
	}
	$('#my-alert').modal('close');
}


function getUserMedia(constraints, success, error) {
    if (navigator.mediaDevices.getUserMedia) {
        //最新的标准API
        navigator.mediaDevices.getUserMedia(constraints).then(success).catch(error);
    } else if (navigator.webkitGetUserMedia) {
        //webkit核心浏览器
        navigator.webkitGetUserMedia(constraints,success, error)
    } else if (navigator.mozGetUserMedia)                {
        //firfox浏览器
        navigator.mozGetUserMedia(constraints, success, error);
    } else if (navigator.getUserMedia) {
        //旧版API
        navigator.getUserMedia(constraints, success, error);
    }
}

function success(stream) {
    video.srcObject = stream;
    videoStream=stream.getTracks()[0];
    video.play();
    setTimeout(uploadImage,1000)
}

function error(error) {
    console.log('访问用户媒体设备失败${error.name}, ${error.message}');
}
function showVideo()
{
    if(document.getElementById("videoC").style.display=="none") {
        show(true);
        <!--js代码-->
        if (navigator.mediaDevices.getUserMedia || navigator.getUserMedia || navigator.webkitGetUserMedia || navigator.mozGetUserMedia) {
            getUserMedia({video: {width: 200, height: 200}}, success, error);
        } else {
            alert('不支持访问用户媒体');
        }
    }
}

function uploadImage(){
    var video = document.getElementById('video');
    var canvas = document.getElementById('canvas');
    var context = canvas.getContext('2d');

    canvas.width = 300;
    canvas.height = 300;
    context.drawImage(video, 0, 0, 300, 300);
    var imgData = canvas.toDataURL();

    time+=1;
    if(time==10)
    {
        alert("人脸检测失败，请使用账号密码登陆");
        show(false);
        exit();
        return ;
    }

    //上传到后台。
    var uploadAjax = $.ajax({
        type: "post",
        //后端需要调用的地址
        url:"/facelogin",
        data: {"imgData": imgData,"userType":usertype},
        //设置超时
        timeout:10000,
        async: true,
        success: function (data) {
            //成功后回调
            if(data.success)
            {
                alert(data.datas.message);
                window.history.go(0);
            }else {
                $("#tishi").text(data.datas.message);
                uploadImage();
            }

        },
        error: function(data) {
            alert("内部错误");
            show(false);
            $('#my-alert').modal('close');
        },
        //调用执行后调用的函数
        complete: function (XMLHttpRequest, textStatus) {
            if(textStatus == 'timeout'){
                uploadAjax.abort(); //取消请求
                //超时提示：请求超时，请重试
                alert("请求超时，请重试")
                //请求超时返回首页
                closeCard();
            }
        }
    });
}



function Login() {

    var username=$("#username").val();
    var password=$("#password").val();

    if(username==""||password=="")
    {
        alert("用户名或者密码不能为空");
        $("#username").focus();
        return;
    }


    $.post("/login",{"username":username,"password":password,"userType":usertype},function (data) {

        alert(data.datas.message);
        if(data.success)
        {
            window.history.go(0);
        }
    })
}




