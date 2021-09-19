/**
 * 图片路径转成canvas
 * @param {图片url} url
 */
async function imgToCanvas(url) {
    // 创建img元素
    const img = document.createElement("img");
    img.src = url;
    img.setAttribute("crossOrigin", "anonymous"); // 防止跨域引起的 Failed to execute 'toDataURL' on 'HTMLCanvasElement': Tainted canvases may not be exported.
    await new Promise((resolve) => (img.onload = resolve));
    // 创建canvas DOM元素，并设置其宽高和图片一样
    const canvas = document.createElement("canvas");
    canvas.width = img.width;
    canvas.height = img.height;
    // 坐标(0,0) 表示从此处开始绘制，相当于偏移。
    canvas.getContext("2d").drawImage(img, 0, 0);
    return canvas;
}

/**
 * canvas添加水印
 * @param {canvas对象} canvas
 * @param {水印文字} text
 */
function addWatermark(canvas, text, fontAndSize, color) {
    const ctx = canvas.getContext("2d");
    ctx.font = fontAndSize;
    ctx.fillStyle = color;
    ctx.textAlign = "center";
    ctx.textBaseline = "middle";
    // 文字水平垂直居中
    ctx.fillText(text, canvas.width / 2.0, canvas.height / 2.0);
    return canvas;
}

/**
 * canvas转成img
 * @param {canvas对象} canvas
 */
function convasToImg(canvas) {
    // 新建Image对象，可以理解为DOM
    let image = new Image();
    // canvas.toDataURL 返回的是一串Base64编码的URL
    // 指定格式 JPG
    image.src = canvas.toDataURL("image/jpg");
    return image;
}

// 运行示例
async function addTextToImg(url, text, fontAndSize, color) {
    const imgUrl = url;
    // 1.图片路径转成canvas
    const tempCanvas = await imgToCanvas(imgUrl);
    // 2.canvas添加水印
    const canvas = addWatermark(tempCanvas, text, fontAndSize, color);
    // 3.canvas转成img
    const img = convasToImg(canvas);
    // 查看效果
    //document.body.appendChild(img);

    return img.src;
}

// 将base64转换为blob类型，目的为了兼容IE
function dataURLtoFile(dataUrl, fileName) {
    let arr = dataUrl.split(','), mime = arr[0].match(/:(.*?);/)[1],
        bStr = atob(arr[1]), n = bStr.length, u8arr = new Uint8Array(n);
    while (n--) {
        u8arr[n] = bStr.charCodeAt(n);
    }

    let blob = new Blob([u8arr], {type: mime});
    blob.lastModifiedDate = new Date();
    blob.name = fileName;

    return blob
}

// 将base64转换为file类型
/*function dataURLtoFile(dataUrl, fileName) {
    let arr = dataUrl.split(','), mime = arr[0].match(/:(.*?);/)[1],
        bStr = atob(arr[1]), n = bStr.length, u8arr = new Uint8Array(n);
    while (n--) {
        u8arr[n] = bStr.charCodeAt(n);
    }
    return new File([u8arr], fileName, {type: mime});
}*/