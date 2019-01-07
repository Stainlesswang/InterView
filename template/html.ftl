<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
	<title>${chineseName}管理</title>
	<head th:replace="include/head"/>
	<#--<script type="text/javascript" th:src="@{{path}/js/jquery/jquery.json-2.2.js(path=${basePath})}"></script>-->
	<#--<script type="text/javascript" th:src="@{{path}/js/jquery-flexigrid/flexigrid.js(path=${basePath})}"></script>-->
	<#--<script type="text/javascript" th:src="@{{path}/js/mJs/ajaxfileupload.js(path=${basePath})}"></script>-->
	<#--<script type="text/javascript" th:src="@{{path}/js/My97DatePicker/WdatePicker.js(path=${basePath})}"></script>-->
	<#--<script type="text/javascript"-->
			<#--th:src="@{{path}/js/jquery-ztree/jquery.ztree.core-3.5.js(path=${basePath})}"></script>-->
	<#--<script type="text/javascript"-->
			<#--th:src="@{{path}/js/jquery-ztree/jquery.ztree.excheck-3.5.js(path=${basePath})}"></script>-->
	<#--<link rel="stylesheet" type="text/css"-->
		  <#--th:href="@{{path}/js/jquery-flexigrid/css/gray/flexigrid.css(path=${basePath})}"/>-->
	<#--<link rel="stylesheet" type="text/css"-->
		  <#--th:href="@{{path}/js/jquery-ztree/zTreeStyle3.5/zTreeStyle.css(path=${basePath})}"/>-->
</head>
<body>
<div class="searchTopDiv">
	<div class="toolItem" style="width:1300px;">
		<div class="toolDiv bg_cde3ff tc clearb">
			<strong class="smallTitle">${chineseName}管理</strong>
		</div>
		<div class="clearb pTB10">
			<table id="addDiv" class="tablePopC" cellspacing="0" cellpadding="0">
				<tr>

					<td style="width:80px; text-align:right;">名称：</td>
					<td id="searchTd"><input id="searchInputTex" class="inputTex" type="text" value=""/></td>
					<td style="width:80px; text-align:right;">类型：</td>
					<td><select id="search_aacid" class="popSel" style="width:100px;" onchange="searchBySearchInput()">
						<option value="">全部</option>
					</select></td>
					<td></td>
					<td>
						<div class="fl">
							<a id="SearchButton" class="bthBlueDeep mgL10" href="javascript:;"
							   onclick="searchBySearchInput();"><i class="bthIco icoZoom"></i>查询</a>
							<a class="bthBlueDeep mgL10" href="javascript:;" onclick="resetSearch()">重置</a>
						</div>
						<div class="fl mgL10 borL_ddd">
							<a class="bthBorderBluePop mgL10" href="javascript:;" onclick="showAddOrEditDiv('add')"><i
									class="bthIco icoAddZoom"></i>新增</a>
						</div>
					</td>
				</tr>
			</table>
		</div>
		<div>
			<table id="TableFlexiGrid"></table>
		</div>
	</div>
</div>
<!-- 上传图片Div -->
<div id="uploadImageDiv" style="display: none;">
	<div class="popPor w560">
		<div class="popTitle">
			<h2 class="titleH2 fl"><span id="uploadImageDivTitle">上传图片</span></h2>
			<a href="javascript:;" onclick="L.closeCurrentDIV();" class="bthPop bthClosed fr"></a>
		</div>
		<div>
			<table class="tablePopB" cellspacing="0" cellpadding="0">
				<tr>
					<td><input id="web_icon_image" type="file" placeholder="图片最大1M" name="file"/></td>
					<#--<!--<th>宽高：<span th:text="${width}"></span>*<span th:text="${height}"></span></th>&ndash;&gt;-->

				</tr>
			</table>
		</div>
		<div class="popBot p10 tr">
			<input id="uploadImageDivConfirm" type="button" class="bthBluePop" value="确定"/>
			<input type="button" class="bthWhitePop" value="取消" onclick="L.closeCurrentDIV();"
				   style="margin-left: 30px"/>
		</div>
	</div>
</div><!-- 上传音频Div -->
<div id="uploadAudioDiv" style="display: none;">
	<div class="popPor w560">
		<div class="popTitle">
			<h2 class="titleH2 fl"><span id="uploadAudioDivTitle">上传音频</span></h2>
			<a href="javascript:;" onclick="L.closeCurrentDIV();" class="bthPop bthClosed fr"></a>
		</div>
		<div>
			<table class="tablePopB" cellspacing="0" cellpadding="0">
				<tr>
					<td><input id="file_Audio" type="file" placeholder="音频最大1M" name="file"/></td>

				</tr>
			</table>
		</div>
		<div class="popBot p10 tr">
			<input id="uploadAudioDivConfirm" type="button" class="bthBluePop" value="确定"/>
			<input type="button" class="bthWhitePop" value="取消" onclick="L.closeCurrentDIV();"
				   style="margin-left: 30px"/>
		</div>
	</div>
</div>
<!-- 添加/修改${chineseName}Div -->
<div id="AddOrEditDiv" style="display: none;width:600px;">
	<div class="popPor w900">
		<div class="popTitle">
			<h2 class="titleH2 fl"><span id="AddOrEditDivTitle"></span></h2>
			<a href="javascript:;" onclick="L.closeAll();" class="bthPop bthClosed fr"></a>
		</div>
		<div class="popCon clearb">
			<table class="tablePopB" cellspacing="0" cellpadding="0">
				<tr id="image_local">
					<td rowspan="1" id="td_image_show" style="width: 120px;height:80px;position:relative;">
						<input type="text" style="display: none" id="edit_imgurl">
						<img id="deletePicture" onclick="deleteWebIcon()" src="../style/images/shan.gif"
							 style="position:absolute;right:0px;top:0px;cursor:pointer;"/>
						<img id="web_icon_show" style="max-width:120px;max-height:80px;"/>
					</td>
					<td rowspan="1">
						<input id="" type="button" class="bthBluePop" onclick="showUploadImageDiv(1)" value="上传图片"/>
						<#--<!--宽高：<span th:text="${width}"></span>*<span th:text="${height}"></span>&ndash;&gt;-->
						宽：<span id="iconwidth">00</span> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;高:<span id="iconheight">00</span>
					</td>
				</tr>
				<tr>
					<td style="width:80px;" align="right"><em style="color: #9e0505">*</em><strong> 音频文件： </strong></td>
					<td colspan="5" align="left">
						<input type="text" id="edit_audiourl" style="display: none" value=""/>
						<audio src="" id="audiourl" controls="controls"></audio>
						<input type="button" class="bthBluePop" onclick="showUploadAudioDiv(1)" value="上传音频"/>
					</td>
				</tr>
				<tr>
					<td style="width:80px;" align="right"><em style="color: #9e0505">*</em><strong> ${chineseName}名称： </strong></td>
					<td colspan="5" align="left">
						<input type="text" class="inputTex" id="edit_name" style="width: 93%;height: 25px" value=""/>
					</td>
				</tr>
				<tr>
					<td style="width:80px;" align="right"><em style="color: #9e0505">*</em><strong> 类型： </strong></td>
					<td colspan="5" align="left">
						<select id="edit_type" class="popSel" style="width: 40%;">

						</select>
					</td>
				</tr>
			</table>
		</div>
		<div class="popBot p10 tr">
			<input id="AddOrEditDivConfirm" type="button" class="bthBluePop" value="确定"/>
			<input type="button" class="bthWhitePop" value="取消" onclick="L.closeAll();" style="margin-left: 30px"/>
		</div>
	</div>
</div>
<script type="text/javascript">
	var urlPath = path + "/${lowClassName}/${lowClassName}_";
	var addPath = "add${upClassName}";
	var deletByIdPath = "delete${upClassName}";
	var updatePath = "update${upClassName}";
	var flexigridPath = "search${upClassName}FG";
	var getBeanByIdPath = "get${upClassName}ById";
	// update${upClassName}IdxById

	var total = 0;
	var globalReturnFlag = -1;
	$(document).ready(function () {
		//加载数据
		initTableFlexiGrid();
		$("#TableFlexiGrid").flexToggleCol(0, false);
		initFile("web_icon_image");
		initFile("file_Audio", true);
		initClassData();
	});

	$("#searchTd").keypress(function (event) {
		if (event.which == "13") {//keyCode=13是回车键
			$('#SearchButton').click();
		}
	});


	function showUploadImageDiv(flag) {
		$("#web_icon_image").val("");
		L.pop({content: '#uploadImageDiv'});
		$("#uploadImageDivConfirm").unbind('click');
		$("#uploadImageDivConfirm").click(function () {
			uploadActivityImage(flag);
		});
	}

	function uploadActivityImage() {
		var img = $("#web_icon_image").val();
		var suffixIndex = img.lastIndexOf(".");
		var suffix = img.substring(suffixIndex + 1).toUpperCase();
		if (null == img || "" == img) {
			L.msg.error("请选择图片！");
		} else if (img != "" && suffix != "BMP" && suffix != "JPG" && suffix != "JPEG" && suffix != "PNG" && suffix != "GIF") {
			L.msg.error("请上传图片（支持BMP、JPG、JPEG、PNG、GIF）!");
			return;
		}
		else {
			L.closeCurrentDIV();
			L.loading(loadType);
			$.ajaxFileUpload({
				url: urlPath + "uploadImage?&tmp=" + Math.round(Math.random() * 100000),
				type: "post",
				dataType: "json",
				timeout: 10000,
				fileElementId: "web_icon_image",// 文件的id
				success: function (str) {
					var data = str;
					L.loadingOff();
					if (ajaxResponseValidate(data)) {
						if (data.code < 1) {
							L.msg.error(data.message, function () {
								L.pop({content: '#uploadImageDiv'});
							});
						} else {
							var bean = data.data;
							L.closeCurrentDIV();
							L.msg.success({content: data.message, time: 1000, position: 'center'});
							//宣传图片回写
							$("#web_icon_show").attr("src", getRandomTfsImagePrefix() + bean.imgtfskey);
							$("#edit_imgurl").val(bean.imgtfskey);
							$("#iconwidth").text(bean.imgw);
							$("#iconheight").text(bean.imgh);
						}
					}
				},
				error: function () {
					L.msg.error("失败！请检查图片大小、格式");
					L.loadingOff();
				}
			});
		}

	}

	function showUploadAudioDiv(flag) {
		$("#file_Audio").val("");
		L.pop({content: '#uploadAudioDiv'});
		$("#uploadAudioDivConfirm").unbind('click');
		$("#uploadAudioDivConfirm").click(function () {
			uploadAudio(flag);
		});
	}

	function uploadAudio() {
		var audio = $("#file_Audio").val();
		var suffixIndex = audio.lastIndexOf(".");
		var ext = audio.substring(suffixIndex + 1).toUpperCase();
		if (null == audio || "" == audio) {
			L.msg.error("请选择音频文件！");
		}
		else if (audio != "" && ext != "MP3" && ext != "WMA" && ext != "AAC" && ext != "WAV" && ext != "CDA") {
			L.msg.error("图片格式不符合要求! [限格式：mp3, wma, aac, wav, cda]");

		} else {
			L.closeCurrentDIV();
			L.loading(loadType);
			$.ajaxFileUpload({
				url: urlPath + "uploadAudio?&tmp=" + Math.round(Math.random() * 100000),
				type: "post",
				dataType: "json",
				timeout: 10000,
				fileElementId: "file_Audio",// 文件的id
				success: function (str) {
					var data = str;
					L.loadingOff();
					if (ajaxResponseValidate(data)) {
						if (data.code < 1) {
							L.msg.error(data.message, function () {
								L.pop({content: '#uploadAudioDiv'});
							});
						} else {
							var audio = data.data;
							L.closeCurrentDIV();
							L.msg.success({content: data.message, time: 1000, position: 'center'});
							$("#audiourl").attr("src", audio);
							$("#edit_audiourl").val(audio);
						}
					}
				},
				error: function () {
					L.msg.error("失败！请检查音频大小、格式");
					L.loadingOff();
				}
			});
		}

	}

	function initTableFlexiGrid() {
		var params = [];
		var search = $.trim($("#searchInputTex").val());
		if (search.length > 0) {
			params.push({name: "aasname", value: search});
		}
		$("#TableFlexiGrid").flexigrid({
			url: urlPath + flexigridPath + "?tmp=" + Math.round(Math.random() * 100000),
			dataType: 'json',
			rp: 10,
			useRp: false,
			colModel: [
				// {
				// 	display: 'ID',
				// 	width: "100",
				// 	align: 'center',
				// 	render: function (val, row) {
				// 		return row.${id};
				// 	}
				// },
				{
					display: '排序',
					width: "100",
					align: 'center',
					visible: 'true',
					render: function (val, row, data) {
						var content = row.idx;
						var html = "";
						if (content != null && content != "") {
							for (var i = 1; i <= data.total; i++) {
								if (i !== content - 0) {
									html += "<option  value=" + i +
											">" + i +
											"</option>"
								} else {
									html += "<option selected='selected' value=" + i +
											">" + i +
											"</option>"
								}
							}
							return "<select  class='table_Select' style='width:80%' onchange='updateAnimalSoundIdxById(\"" + row.${id} + "\",this.options[this.options.selectedIndex].value)'>" + html + "</select>";
						} else {
							return row.idx;
						}

					}
				},
				{
					display: '${chineseName}名称',
					width: "120",
					align: 'center',
					render: function (val, row) {
						var content = stringFilter(row.aasname);
						return "<span style=\"word-break: break-all; word-wrap: break-word; display: block;\">" + content + "</span>";
					}
				},
				{
					display: '类型',
					width: "100",
					align: 'center',
					render: function (val, row) {
						return $("#search_aacid option[value=" + row.aacid + "]").text();
					}
				},
				{
					display: '图片',
					width: "150",
					align: 'center',
					render: function (val, row) {
						if (null == row.imgtfskey || '' == row.imgtfskey) {
							return "";
						} else {
							return "<a href='" + getRandomTfsImagePrefix() + row.imgtfskey + "' target='_blank'><img src='" + getRandomTfsImagePrefix() + row.imgtfskey + " 'style='cursor:pointer;max-width:75px;max-height:75px;'/></a>";
						}
					}
				},
				{
					display : '音频链接',
					width : "320",
					align : 'center' ,
					render : function(val,row){
						if (row.audiourl == null || row.audiourl == "") {
							return "";
						} else{
							return "<a href='" + row.audiourl + "' target='_blank' style='word-break: break-all; word-wrap: break-word; display: block;'>" + row.audiourl + "</a>";
						}
					}
				},
				{
					display: '操作',
					width: "200",
					align: 'center',
					render: function (val, row) {
						var result = "";
						result += "<input  type='button' class='bthBlueOper' value='修改' onclick='showAddOrEditDiv(\"edit\", \"" + row.${id} + "\")'/>";
						result += "<input type='button' class='bthBlueOper mgL10' value='删除' onclick='deleteLocation(\"" + row.${id} + "\",\"" + row.aasname + "\")'/>";
						return result;
					}
				},
				{
					display: '创建人/创建时间',
					width: "110",
					align: 'center',
					render: function (val, row) {
						var userName = row.tmp_creatorName == null ? "" : row.tmp_creatorName;
						var update = row.createtime == null ? "" : row.createtime.substring(0, 19);
						return userName + "</br>" + update;
					}
				},
				{
					display: '更新人/更新时间',
					width: "110",
					align: 'center',
					render: function (val, row) {
						var userName = row.tmp_updateUserName == null ? "" : row.tmp_updateUserName;
						var update = row.updatetime == null ? "" : row.updatetime.substring(0, 19);
						return userName + "</br>" + update;
					}
				}
			],
			width: "auto",
			height: "auto",
			addparams: params,
			sortname: "createtime",
			sortorder: "desc",
			showToggleBtn: true,
			showTableToggleBtn: true,
			onSubmit: function () {
				L.loading(loadType);
				return true;
			},
			onSuccess: function (grid, data) {
				var search = $.trim($("#searchInputTex").val());
				if (!search.length > 0) {
					total = data.total;
				}
				L.loadingOff();
				return true;
			},
			onError: function () {
				<#--L.msg.error("[(${ajaxErrorInfo})]");-->
				L.loadingOff();
				return true;
			}
		});
	}

	/** 重置查询${chineseName}  */
	function resetSearch() {
		$("#searchInputTex").val("");
		$("#search_aacid").val("");
		var params = [];
		$("#TableFlexiGrid").flexOptions({
			newp: 1,
			addparams: params
		}).flexReload();
		$("#TableFlexiGrid").flexToggleCol(0, false);
	}

	/** 查询  */
	function searchBySearchInput() {
		var params = [];
		var search = $.trim($("#searchInputTex").val());
		var type = $.trim($("#search_aacid").val());
		if (search.length > 0) {
			params.push({name: "aasname", value: search});
		}
		if (type.length > 0) {
			params.push({name: "aacid", value: type});
		}
		if (type.length > 0 && search.length <= 0) {
			$("#TableFlexiGrid").flexOptions({
				newp: 1,
				addparams: params,
				sortname: 'idx',
				sortorder: 'asc'
			}).flexReload();
			$("#TableFlexiGrid").flexToggleCol(0, true);
		} else {
			$("#TableFlexiGrid").flexOptions({
				newp: 1,
				addparams: params
			}).flexReload();
			$("#TableFlexiGrid").flexToggleCol(0, false);
		}
	}

	/** 重置${chineseName}添加表单  */
	function resetLocationDiv() {
		// $("#name").removeAttr('disabled').removeClass("textGrayColor");
		$("#edit_type").val("");
		$("#iconwidth").text('00');
		$("#iconheight").text('00');
		$("#web_icon_image").val("");
		$("#edit_imgurl").val("");
		$("#file_Audio").removeAttr("src");
		$("#web_icon_show").removeAttr("src");
		$("#edit_audiourl").val("");
		$("#audiourl").removeAttr("src");
		$("#deletePicture").removeAttr("src");
		$("#AddOrEditDiv").find("input[type='text']").val("");
	}

	/** 弹出${chineseName}添加/编辑页面*/
	function showAddOrEditDiv(model, id) {
		resetLocationDiv();
		if (model == "add") {
			$("#AddOrEditDivTitle").text("添加${chineseName}");
			$("#AddOrEditDivConfirm").unbind();
			$("#AddOrEditDivConfirm").click(function () {
				addBean();
			});
			L.pop({content: '#AddOrEditDiv'});
			$("#edit_name").focus();

		} else if (model == "edit") {
			$.ajax({
				url: urlPath + getBeanByIdPath + "?tmp=" + Math.round(Math.random() * 100000),
				data: {id: id},
				type: "post",
				dataType: "json",
				beforeSend: function () {
					L.loading(loadType);
				},
				success: function (data) {
					if (ajaxResponseValidate(data)) {
						if (data.code < 1) {
							L.msg.error(data.message, function () {
								L.loadingOff();
							});
							$("#TableFlexiGrid").flexReload();
						} else {
							var obj = data.data;
							$("#AddOrEditDivTitle").text("修改");
							$("#web_icon_image").val("");
							var imgurl = getRandomTfsImagePrefix() + obj.imgtfskey
							$("#web_icon_show").attr("src", imgurl);
							$("#edit_imgurl").val(obj.imgtfskey);
							$("#iconwidth").text(obj.imgw);
							$("#iconheight").text(obj.imgh);
							if (null != obj.imgtfskey && '' != obj.imgtfskey) {
								$("#deletePicture").attr("src", "../style/images/shan.gif");
							}
							$("#edit_audiourl").val(obj.audiourl);
							$("#audiourl").attr("src", obj.audiourl);
							$("#edit_name").val(obj.aasname);
							$("#edit_type").val(obj.aacid);
							$("#AddOrEditDivConfirm").unbind();
							keyDownAction(function (event) {
								if (event.which == "13") {
									editBean(obj.${id}, obj);
								}
							});
							$("#AddOrEditDivConfirm").click(function () {
								editBean(obj.${id}, obj);
							});
							L.loadingOff();
							L.pop({content: '#AddOrEditDiv'});
						}
					}
				},
				error: function () {
					L.msg.error("[(${ajaxErrorInfo})]");
					L.loadingOff();
				}
			});
		}
	}

	/** 添加  */
	function addBean() {
		var bean = {};
		var result = checkBeanForm("add", bean, null);
		if (globalReturnFlag == result) {
			return false;
		} else if (false == result) {
			L.closeAll();
			return false;
		}
		$.ajax({
			url: urlPath + addPath + "?tmp=" + Math.round(Math.random() * 100000),
			data: bean,
			type: "post",
			dataType: "json",
			beforeSend: function () {
				L.closeAll();
				L.loading(loadType);
			},
			success: function (data) {
				L.loadingOff();
				if (ajaxResponseValidate(data)) {
					if (data.code < 1) {
						L.msg.error(data.message, function () {
							L.pop({content: '#AddOrEditDiv'});
						});
					} else {
						var mesaage = data.message + (null != data.data ? data.data : "");
						L.msg.success({content: mesaage, time: 1000, position: 'center'});
						$("#TableFlexiGrid").flexReload();
					}
				}
			},
			error: function () {
				L.msg.error("[(${ajaxErrorInfo})]");
				L.loadingOff();
			}
		});
	}

	/** 修改  */
	function editBean(Id, oldBean) {
		var bean = {};
		var result = checkBeanForm("edit", bean, oldBean);
		if (globalReturnFlag == result) {
			return false;
		} else if (false == result) {
			L.closeAll();
			return false;
		}
		bean["${id}"] = Id;
		$.ajax({
			url: urlPath + updatePath + "?tmp=" + Math.round(Math.random() * 100000),
			data: bean,
			type: "post",
			dataType: "json",
			beforeSend: function () {
				L.closeAll();
				L.loading(loadType);
			},
			success: function (data) {
				L.loadingOff();
				if (ajaxResponseValidate(data)) {
					if (data.code < 1) {
						L.msg.error(data.message, function () {
							L.pop({content: '#AddOrEditDiv'});
						});
					} else {
						L.msg.success({content: data.message, time: 1000, position: 'center'});
						$("#TableFlexiGrid").flexReload();
					}
				}
			},
			error: function () {
				L.msg.error("[(${ajaxErrorInfo})]");
				L.loadingOff();
			}
		});
	}

	/** 删除 */
	function deleteLocation(Id, realName) {
		L.msg.confirm({
			content: "确定要删除 [" + realName + "] 吗？", yes: function () {
				$.ajax({
					url: urlPath + deletByIdPath + "?tmp=" + Math.round(Math.random() * 100000),
					data: {id: Id},
					type: "post",
					dataType: "json",
					beforeSend: function () {
						L.loading(loadType);
					},
					success: function (data) {
						L.loadingOff();
						if (ajaxResponseValidate(data)) {
							if (data.code < 1) {
								L.msg.error(data.message);
							} else {
								L.msg.success({content: data.message, time: 1000, position: 'center'});
								$("#TableFlexiGrid").flexReload();
							}
						}
					},
					error: function () {
						L.msg.error("[(${ajaxErrorInfo})]");
						L.loadingOff();
					}
				});
			}
		});
	}

	/** 更新块排序*/
	function updateAnimalSoundIdxById(id, idx) {
		var bean = {};
		bean["${id}"] = id;
		bean["idx"] = idx;
		$.ajax({
			url: urlPath + "update${upClassName}IdxById?tmp=" + Math.round(Math.random() * 100000),
			data: bean,
			type: "post",
			dataType: "json",
			beforeSend: function () {
				L.loading(loadType);
			},
			success: function (data) {
				L.loadingOff();
				if (ajaxResponseValidate(data)) {
					if (data.code < 1) {
						L.msg.error(data.message);
					} else {
						L.msg.success({content: data.message, time: 1000, position: 'center'});
						// $("#TableFlexiGrid").flexOptions({
						// 	newp: 1,
						// 	sortname: 'idx',
						// 	sortorder: 'asc'
						// }).flexReload();
						$("#TableFlexiGrid").flexReload();
					}
				}
			},
			error: function () {
				L.msg.error("[(${ajaxErrorInfo})]");
				L.loadingOff();
			}
		});
	}

	function deleteWebIcon() {
		L.msg.confirm({
			content: "确定删除图片吗？", yes: function () {
				L.closeAll();
				L.loading(loadType);
				$("#web_icon_show").removeAttr("src");
				$("#edit_imgurl").val("");
				$("#iconwidth").text('00');
				$("#iconheight").text('00');
				$("#deletePicture").removeAttr("src");
				L.loadingOff();
				L.pop({content: '#AddOrEditDiv'});
			}
		});
	}

	/*获取信息*/
	function initClassData() {
		$.ajax({
			url: urlPath + "getAnimalClass?tmp=" + Math.round(Math.random() * 100000),
			type: "get",
			dataType: "json",
			async: false,
			beforeSend: function () {
				L.loading(loadType);
			},
			success: function (data) {
				L.loadingOff();
				if (ajaxResponseValidate(data)) {
					if (data.code < 1) {
						L.msg.error(data.message);
					} else {
						var classify = data.data;
						if (null != classify && 0 < classify.length) {
							$("#search_aacid").html("").append("<option value=\"\">请选择</option>");
							$("#edit_type").html("").append("<option value=\"\">请选择</option>");
							$.each(classify, function (k, v) {
								$("#search_aacid").append("<option value='" + v.aacid + "'>" + v.aacname + "</option>");
								$("#edit_type").append("<option value='" + v.aacid + "'>" + v.aacname + "</option>");
							});
						}
					}
				}
			},
			error: function () {
				L.msg.error("[(${ajaxErrorInfo})]");
				L.loadingOff();
			}
		});
	}

	//验证添加/检查表单数据
	function checkBeanForm(mode, objBean, oldBean) {
		var name = $.trim($("#edit_name").val());
		var aacid = $.trim($("#edit_type").val());
		var imgurl = $("#edit_imgurl").val();
		var audiourl = $("#edit_audiourl").val();
		var width = $("#iconwidth").text();
		var height = $("#iconheight").text();
		var message = "";
		var changed = ("add" == mode);
		var modeFlag = ("edit" == mode);
		//开始验证参数
		message = checkStrParam(imgurl, 512, false);
		if ("" != message) {
			L.msg.error("图片" + message);
			return globalReturnFlag;
		}
		message = checkStrParam(audiourl, 512, false);
		if ("" != message) {
			L.msg.error("音频" + message);
			return globalReturnFlag;
		}
		message = checkStrParam(name, 49, false);
		if ("" != message) {
			L.msg.error("${chineseName}名称" + message);
			return globalReturnFlag;
		}
		message = checkStrParam(aacid, 20, false);
		if ("" != message) {
			L.msg.error("类型" + message);
			return globalReturnFlag;
		}
		objBean["aasname"] = name;
		objBean["imgtfskey"] = imgurl;
		objBean["audiourl"] = audiourl;
		objBean["imgw"] = width;
		objBean["imgh"] = height;
		objBean["aacid"] = aacid;
		if (modeFlag && !changed) {
			if (name != oldBean.aasname) {
				changed = true;
			}
		}
		if (modeFlag && !changed) {
			var beanlogo = oldBean.imgtfskey == null ? '' : oldBean.imgtfskey;
			if (!changed && imgurl != beanlogo) {
				changed = true;
			}
		}
		if (modeFlag && !changed) {
			var beanaudiourl = oldBean.audiourl == null ? '' : oldBean.audiourl;
			if (!changed && audiourl != beanaudiourl) {
				changed = true;
			}
		}
		if (modeFlag && !changed) {
			var beanaacid = oldBean.aacid == null ? '' : oldBean.aacid;
			if (!changed && aacid != beanaacid) {
				changed = true;
			}
		}
		return changed;
	}

	/*]]>*/
</script>
</body>
</html>