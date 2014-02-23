package com.audamob.doit.tool.utils.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapShader;
import android.graphics.BlurMaskFilter;
import android.graphics.BlurMaskFilter.Blur;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;
import android.renderscript.Allocation;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.util.Log;

/**
 * @author Aymen
 *
 */
/**
 * @author Aymen
 *
 */
/**
 * @author Aymen
 *
 */
public class GraphicsUtil {

	/*
	 * Draw image in circular shape Note: change the pixel size if you want
	 * image small or large
	 */
	public static Bitmap getCircleBitmap(Bitmap bitmap, int pixels) {
		Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
				bitmap.getHeight(), Bitmap.Config.ARGB_8888);
		Canvas canvas = new Canvas(output);

		final int color = 0xffff0000;
		final Paint paint = new Paint();
		final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
		final RectF rectF = new RectF(rect);

		paint.setAntiAlias(true);
		paint.setDither(true);
		paint.setFilterBitmap(true);
		canvas.drawARGB(0, 0, 0, 0);
		paint.setColor(color);
		canvas.drawOval(rectF, paint);

		paint.setColor(Color.BLUE);
		paint.setStyle(Paint.Style.STROKE);
		paint.setStrokeWidth((float) 4);
		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		canvas.drawBitmap(bitmap, rect, rect, paint);

		return output;
	}

	public static Bitmap GetBitmapClippedCircle(Bitmap bitmap) {

		final int width = bitmap.getWidth();
		final int height = bitmap.getHeight();
		final Bitmap outputBitmap = Bitmap.createBitmap(width, height,
				Config.ARGB_8888);
		BitmapShader shader = new BitmapShader(bitmap, TileMode.CLAMP,
				TileMode.CLAMP);

		Paint paint = new Paint();
		paint.setShader(shader);
		paint.setAntiAlias(true);
		paint.setFilterBitmap(true);
		paint.setDither(true);
		paint.setStyle(Paint.Style.STROKE);
		paint.setColor(Color.parseColor("#BAB399"));

		final Path path = new Path();
		path.addCircle((float) (width / 2), (float) (height / 2),
				(float) Math.min(width, (height / 2)), Path.Direction.CCW);

		final Canvas canvas = new Canvas(outputBitmap);
		canvas.clipPath(path);

		canvas.drawBitmap(bitmap, 0, 0, paint);

		return outputBitmap;
	}

	public static Bitmap highlightImage(Bitmap src) {

		// create new bitmap, which will be painted and becomes result image
		Bitmap bmOut = Bitmap.createBitmap(src.getWidth() + 126,
				src.getHeight() + 126, Bitmap.Config.ARGB_8888);
		// setup canvas for painting
		Canvas canvas = new Canvas(bmOut);
		// setup default color
		canvas.drawColor(0, PorterDuff.Mode.CLEAR);
		// create a blur paint for capturing alpha
		Paint ptBlur = new Paint();
		ptBlur.setMaskFilter(new BlurMaskFilter(15, Blur.NORMAL));
		int[] offsetXY = new int[2];
		// capture alpha into a bitmap
		Bitmap bmAlpha = src.extractAlpha(ptBlur, offsetXY);
		// create a color paint
		Paint ptAlphaColor = new Paint();
		ptAlphaColor.setColor(0x01A9DB);
		// paint color for captured alpha region (bitmap)
		canvas.drawBitmap(bmAlpha, offsetXY[0], offsetXY[1], ptAlphaColor);
		// free memory
		bmAlpha.recycle();

		// paint the image source
		canvas.drawBitmap(src, 0, 0, null);

		// return out final image
		return bmOut;
	}

	public static Bitmap addRoundedBorder(Bitmap bitmap) {
		int w = bitmap.getWidth();
		int h = bitmap.getHeight();

		int radius = Math.min(h / 2, w / 2);
		Bitmap output = Bitmap.createBitmap(w + 8, h + 8, Config.ARGB_8888);

		Paint p = new Paint();
		p.setAntiAlias(true);

		Canvas c = new Canvas(output);
		c.drawARGB(0, 0, 0, 0);
		p.setStyle(Style.FILL);

		c.drawCircle((w / 2) + 4, (h / 2) + 4, radius, p);

		p.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));

		c.drawBitmap(bitmap, 4, 4, p);
		p.setXfermode(null);
		p.setStyle(Style.STROKE);
		p.setColor(Color.WHITE);
		p.setStrokeWidth(3);
		c.drawCircle((w / 2) + 4, (h / 2) + 4, radius, p);

		return output;
	}
	
	/**
	 * Rounded Image sans degradation
	 * 
	 */
	public static Bitmap getRoundedBitmap(Bitmap bitmap) {
	    final Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
	    final Canvas canvas = new Canvas(output);
	 
	    final int color = Color.RED;
	    final Paint paint = new Paint();
	    final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
	    final RectF rectF = new RectF(rect);
	 
	    paint.setAntiAlias(true);
	    canvas.drawARGB(0, 0, 0, 0);
	    paint.setColor(color);
	    canvas.drawOval(rectF, paint);
	 
	    paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
	    canvas.drawBitmap(bitmap, rect, rect, paint);
	 
	    bitmap.recycle();
	 
	    return output;
	  }
	public static Bitmap BlurImage (Bitmap input,Context context)
	{
		RenderScript rsScript = RenderScript.create (context);
		Log.d("DoItEvryWhere","niveau de code apres blurred 1");
		Allocation alloc = Allocation.createFromBitmap (rsScript, input);
		Log.d("DoItEvryWhere","niveau de code apres blurred 2");
		ScriptIntrinsicBlur blur = ScriptIntrinsicBlur.create(rsScript, alloc.getElement());
		
		Log.d("DoItEvryWhere","niveau de code apres blurred 3");
		blur.setRadius (25);
		Log.d("DoItEvryWhere","niveau de code apres blurred 5");
		blur.setInput (alloc);
		Log.d("DoItEvryWhere","niveau de code apres blurred 6");

		Bitmap result = Bitmap.createBitmap (input.getWidth(), input.getHeight(), input.getConfig ());
		Log.d("DoItEvryWhere","niveau de code apres blurred 7");
		Allocation outAlloc = Allocation.createFromBitmap(rsScript, result);
		blur.forEach(outAlloc);
		outAlloc.copyTo (result);
		Log.d("DoItEvryWhere","niveau de code apres blurred 4"+result);
		rsScript.destroy ();
		return result;
		
	}
	
	
	/**
	 * Aymen Atia
	 * @param sentBitmap
	 * @param radius
	 * @return blurred bitmap
	 */
	public static Bitmap fastblur(Bitmap sentBitmap, int radius) {
        Bitmap bitmap = sentBitmap.copy(sentBitmap.getConfig(), true);

        if (radius < 1) {
            return (null);
        }

        int w = bitmap.getWidth();
        int h = bitmap.getHeight();

        int[] pix = new int[w * h];
        Log.e("pix", w + " " + h + " " + pix.length);
        bitmap.getPixels(pix, 0, w, 0, 0, w, h);

        int wm = w - 1;
        int hm = h - 1;
        int wh = w * h;
        int div = radius + radius + 1;

        int r[] = new int[wh];
        int g[] = new int[wh];
        int b[] = new int[wh];
        int rsum, gsum, bsum, x, y, i, p, yp, yi, yw;
        int vmin[] = new int[Math.max(w, h)];

        int divsum = (div + 1) >> 1;
        divsum *= divsum;
        int dv[] = new int[256 * divsum];
        for (i = 0; i < 256 * divsum; i++) {
            dv[i] = (i / divsum);
        }

        yw = yi = 0;

        int[][] stack = new int[div][3];
        int stackpointer;
        int stackstart;
        int[] sir;
        int rbs;
        int r1 = radius + 1;
        int routsum, goutsum, boutsum;
        int rinsum, ginsum, binsum;

        for (y = 0; y < h; y++) {
            rinsum = ginsum = binsum = routsum = goutsum = boutsum = rsum = gsum = bsum = 0;
            for (i = -radius; i <= radius; i++) {
                p = pix[yi + Math.min(wm, Math.max(i, 0))];
                sir = stack[i + radius];
                sir[0] = (p & 0xff0000) >> 16;
                sir[1] = (p & 0x00ff00) >> 8;
                sir[2] = (p & 0x0000ff);
                rbs = r1 - Math.abs(i);
                rsum += sir[0] * rbs;
                gsum += sir[1] * rbs;
                bsum += sir[2] * rbs;
                if (i > 0) {
                    rinsum += sir[0];
                    ginsum += sir[1];
                    binsum += sir[2];
                } else {
                    routsum += sir[0];
                    goutsum += sir[1];
                    boutsum += sir[2];
                }
            }
            stackpointer = radius;

            for (x = 0; x < w; x++) {

                r[yi] = dv[rsum];
                g[yi] = dv[gsum];
                b[yi] = dv[bsum];

                rsum -= routsum;
                gsum -= goutsum;
                bsum -= boutsum;

                stackstart = stackpointer - radius + div;
                sir = stack[stackstart % div];

                routsum -= sir[0];
                goutsum -= sir[1];
                boutsum -= sir[2];

                if (y == 0) {
                    vmin[x] = Math.min(x + radius + 1, wm);
                }
                p = pix[yw + vmin[x]];

                sir[0] = (p & 0xff0000) >> 16;
                sir[1] = (p & 0x00ff00) >> 8;
                sir[2] = (p & 0x0000ff);

                rinsum += sir[0];
                ginsum += sir[1];
                binsum += sir[2];

                rsum += rinsum;
                gsum += ginsum;
                bsum += binsum;

                stackpointer = (stackpointer + 1) % div;
                sir = stack[(stackpointer) % div];

                routsum += sir[0];
                goutsum += sir[1];
                boutsum += sir[2];

                rinsum -= sir[0];
                ginsum -= sir[1];
                binsum -= sir[2];

                yi++;
            }
            yw += w;
        }
        for (x = 0; x < w; x++) {
            rinsum = ginsum = binsum = routsum = goutsum = boutsum = rsum = gsum = bsum = 0;
            yp = -radius * w;
            for (i = -radius; i <= radius; i++) {
                yi = Math.max(0, yp) + x;

                sir = stack[i + radius];

                sir[0] = r[yi];
                sir[1] = g[yi];
                sir[2] = b[yi];

                rbs = r1 - Math.abs(i);

                rsum += r[yi] * rbs;
                gsum += g[yi] * rbs;
                bsum += b[yi] * rbs;

                if (i > 0) {
                    rinsum += sir[0];
                    ginsum += sir[1];
                    binsum += sir[2];
                } else {
                    routsum += sir[0];
                    goutsum += sir[1];
                    boutsum += sir[2];
                }

                if (i < hm) {
                    yp += w;
                }
            }
            yi = x;
            stackpointer = radius;
            for (y = 0; y < h; y++) {
                // Preserve alpha channel: ( 0xff000000 & pix[yi] )
                pix[yi] = ( 0xff000000 & pix[yi] ) | ( dv[rsum] << 16 ) | ( dv[gsum] << 8 ) | dv[bsum];

                rsum -= routsum;
                gsum -= goutsum;
                bsum -= boutsum;

                stackstart = stackpointer - radius + div;
                sir = stack[stackstart % div];

                routsum -= sir[0];
                goutsum -= sir[1];
                boutsum -= sir[2];

                if (x == 0) {
                    vmin[y] = Math.min(y + r1, hm) * w;
                }
                p = x + vmin[y];

                sir[0] = r[p];
                sir[1] = g[p];
                sir[2] = b[p];

                rinsum += sir[0];
                ginsum += sir[1];
                binsum += sir[2];

                rsum += rinsum;
                gsum += ginsum;
                bsum += binsum;

                stackpointer = (stackpointer + 1) % div;
                sir = stack[stackpointer];

                routsum += sir[0];
                goutsum += sir[1];
                boutsum += sir[2];

                rinsum -= sir[0];
                ginsum -= sir[1];
                binsum -= sir[2];

                yi += w;
            }
        }

        Log.e("pix", w + " " + h + " " + pix.length);
        bitmap.setPixels(pix, 0, w, 0, 0, w, h);

        return (bitmap);
    }
}
