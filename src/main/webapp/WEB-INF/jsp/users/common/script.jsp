<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript">
var isFollowingURL="${x}/users/isFollowing?id=${user.id}";
var followURL="${x}/users/follow?id=${user.id}";
var unfollowURL="${x}/users/unfollow?id=${user.id}";
$(function (){
	$.getJSON(isFollowingURL,function (msg){
		if(msg==0){
			$("#btn-follow").html("追随").show();
		}else{
			$("#btn-follow").html("追随着").show();
		}
	})
});
$("#btn-follow").on("click",function (e){
	$.getJSON(isFollowingURL,function (msg){
		if(msg==0){
			$.getJSON(followURL,function (ok){
				if(ok==1){
					$("#btn-follow").html("追随着");
				}
			});
		}else{
			$.getJSON(unfollowURL,function (done){
				if(done==1){
					$("#btn-follow").html("追随");
				}
			});
		}
	})
	return true;
})
</script>