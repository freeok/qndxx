/**
 * 图片加文字
 * @param  base64 图片base64编码
 * @param  text 文字内容
 * @param color 文字颜色
 * @return string 图片base64编码
 */
async function imgAddText(base64, text, color) {
  // 1.base64编码转canvas
  const tempCanvas = await imgToCanvas(base64);
  // 2.canvas添加水印
  const canvas = addWatermark(tempCanvas, text, color);
  // 3.canvas转成img
  const img = canvasToImg(canvas);
  // 查看效果
  //document.body.appendChild(img);
  return img.src;
}

// 将base64转换为blob类型，目的为了兼容IE
function base642Blob(dataUrl, fileName) {
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

/**
 * base64编码 => canvas
 */
async function imgToCanvas(base64) {
  // 创建img元素
  const img = document.createElement("img");
  img.src = base64;
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
 * canvas 添加水印
 */
function addWatermark(canvas, text, color) {
  let maxSize = 80; // 最大字体大小
  let minSize = 10; // 最小字体大小
  let maxWidth = canvas.width * 0.8; // 文字允许的最大宽度

  const ctx = canvas.getContext("2d");
  // 从最大字体大小开始尝试递减字体大小，直到文字宽度小于最大宽度
  let fontSize = maxSize;
  // 文字宽度
  let textWidth
  // 文字大小自适应
  do {
    ctx.font = fontSize + 'px 微软雅黑';
    textWidth = ctx.measureText(text).width;
    fontSize--;
  } while (textWidth > maxWidth && fontSize >= minSize);
  console.log(`自适应文字大小：${fontSize}px 文字宽度：${textWidth}`)

  // ctx.font = font;
  ctx.fillStyle = color;
  ctx.textAlign = "center";
  ctx.textBaseline = "middle";
  // 文字水平垂直居中
  ctx.fillText(text, canvas.width / 2, canvas.height / 2);
  return canvas;
}

/**
 * canvas => img
 * @param canvas canvas对象
 */
function canvasToImg(canvas) {
  // 新建Image对象，可以理解为DOM
  let image = new Image();
  // canvas.toDataURL 返回的是一串Base64编码的URL
  // 指定格式 JPG
  image.src = canvas.toDataURL("image/jpg");
  return image;
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