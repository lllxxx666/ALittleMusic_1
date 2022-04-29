package nd.no.xww.bottomnavigationlayout

import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.Drawable
import androidx.palette.graphics.Palette
import androidx.palette.graphics.Palette.PaletteAsyncListener
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.alittlemusic.baseClass.MyApplication.Companion.context


/**
 * @author liuxin
 * @desciption : Palette 提取图片主色调
 */

//private fun getPalette(bitmap: Bitmap?) {
//    if (bitmap != null) {
//        Palette.from(bitmap).generate { palette ->
//            if (palette != null) {
//                val vibrant = palette.vibrantSwatch //有活力的
////                if (vibrant != null) {
////                    tvVibrant!!.setBackgroundColor(vibrant.rgb)
////                    tvVibrantBurn!!.setBackgroundColor(setColorBurn(vibrant.rgb, 0.3f))
////                }
//                val vibrantDark = palette.darkVibrantSwatch //有活力的暗色
//
//                val vibrantLight = palette.lightVibrantSwatch //有活力的亮色
//
//                val muted = palette.mutedSwatch //柔和的
//
//                val mutedDark = palette.darkMutedSwatch //柔和的暗色
//
//                val mutedLight = palette.lightMutedSwatch //柔和的亮色
//
//            }
//        }
//    }
//}
//
//private fun getBitmap(imageView: ImageView?): Bitmap {
//    return (imageView!!.drawable as BitmapDrawable).bitmap
//}
//



/**
 * 使用Glide获取网络图片并转换为Bitmap
 *
 * @param path 图片地址
 */
//fun asBitmap(path: String) {
//    Glide.with(Activity()).asBitmap()
//        .load(path)
//        .into(object : SimpleTarget<Bitmap?>() {
//            override fun onResourceReady(
//                bitmap: Bitmap,
//                @Nullable transition: Transition<in Bitmap?>?
//            ) {
//                paletteBitmap(bitmap)
//            }
//        })
//}

/**
 * 加载网络地址 [url] 图片返回 Bitmap
 */
fun asBitmap(url: String, success: (Bitmap) -> Unit) {
    Glide.with(context) // context，可添加到参数中
        .asBitmap()
        .load(url)
        .into(object : CustomTarget<Bitmap>() {
            override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                // 成功返回 Bitmap
                success.invoke(resource)
            }

            override fun onLoadCleared(placeholder: Drawable?) {

            }
        })
}



/**
 * 异步抽取图片色调方法
 *
 * @param bitmap Bitmap对象
 */
fun paletteBitmap(bitmap: Bitmap) {
    Palette.from(bitmap) //创建Palette.Builder
        .generate(object : PaletteAsyncListener {
            //异步抽取图片色调方法

            override fun onGenerated(palette: Palette?) {
                //获取到充满活力的这种色调
                val vibrantSwatch = palette?.vibrantSwatch

                //获取充满活力的黑
                val darkVibrantSwatch = palette?.darkVibrantSwatch

                //获取充满活力的亮
                val lightVibrantSwatch = palette?.lightVibrantSwatch

                //获取柔和的色调
                val mutedSwatch = palette?.mutedSwatch

                //获取柔和的黑
                val darkMutedSwatch = palette?.darkMutedSwatch

                //获取柔和的亮
                val lightMutedSwatch = palette?.lightMutedSwatch
            }
        })
}
/**
 * 颜色加深算法
 */
private fun setColorBurn(rgb: Int, `val`: Float): Int {
    var r = rgb shr 16 and 0xff
    var g = rgb shr 8 and 0xff
    var b = rgb and 0xff
    r = Math.floor((r * (1f - `val`)).toDouble()).toInt()
    g = Math.floor((g * (1f - `val`)).toDouble()).toInt()
    b = Math.floor((b * (1f - `val`)).toDouble()).toInt()
    return Color.rgb(r, g, b)
}

/**
 * 颜色浅化算法
 */
private fun setColorShallow(rgb: Int, `val`: Float): Int {
    var r = rgb shr 16 and 0xff
    var g = rgb shr 8 and 0xff
    var b = rgb and 0xff
    r = Math.floor((r * (1f + `val`)).toDouble()).toInt()
    g = Math.floor((g * (1f + `val`)).toDouble()).toInt()
    b = Math.floor((b * (1f + `val`)).toDouble()).toInt()
    return Color.rgb(r, g, b)
}