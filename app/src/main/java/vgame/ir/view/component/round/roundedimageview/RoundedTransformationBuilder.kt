/*
* Copyright (C) 2017 Vincent Mi
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*      http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package vgame.ir.view.component.round.roundedimageview

import android.content.res.ColorStateList
import android.content.res.Resources
import android.graphics.Bitmap
import android.util.DisplayMetrics
import android.util.TypedValue
import android.widget.ImageView
import com.squareup.picasso.Transformation
import java.util.Arrays

class RoundedTransformationBuilder {

    private val mDisplayMetrics: DisplayMetrics

    private val mCornerRadii = floatArrayOf(0f, 0f, 0f, 0f)

    private var mOval = false
    private var mBorderWidth = 0f
    private var mBorderColor = ColorStateList.valueOf(RoundedDrawable.DEFAULT_BORDER_COLOR)
    private var mScaleType: ImageView.ScaleType = ImageView.ScaleType.FIT_CENTER

    init {
        mDisplayMetrics = Resources.getSystem().displayMetrics
    }

    fun scaleType(scaleType: ImageView.ScaleType): RoundedTransformationBuilder {
        mScaleType = scaleType
        return this
    }

    /**
     * Set corner radius for all corners in px.
     *
     * @param radius the radius in px
     * @return the builder for chaining.
     */
    fun cornerRadius(radius: Float): RoundedTransformationBuilder {
        mCornerRadii[Corner.TOP_LEFT] = radius
        mCornerRadii[Corner.TOP_RIGHT] = radius
        mCornerRadii[Corner.BOTTOM_RIGHT] = radius
        mCornerRadii[Corner.BOTTOM_LEFT] = radius
        return this
    }

    /**
     * Set corner radius for a specific corner in px.
     *
     * @param corner the corner to set.
     * @param radius the radius in px.
     * @return the builder for chaning.
     */
    fun cornerRadius(@Corner corner: Int, radius: Float): RoundedTransformationBuilder {
        mCornerRadii[corner] = radius
        return this
    }

    /**
     * Set corner radius for all corners in density independent pixels.
     *
     * @param radius the radius in density independent pixels.
     * @return the builder for chaining.
     */
    fun cornerRadiusDp(radius: Float): RoundedTransformationBuilder {
        return cornerRadius(
                TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, radius, mDisplayMetrics))
    }

    /**
     * Set corner radius for a specific corner in density independent pixels.
     *
     * @param corner the corner to set
     * @param radius the radius in density independent pixels.
     * @return the builder for chaining.
     */
    fun cornerRadiusDp(@Corner corner: Int, radius: Float): RoundedTransformationBuilder {
        return cornerRadius(corner,
                TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, radius, mDisplayMetrics))
    }

    /**
     * Set the border width in pixels.
     *
     * @param width border width in pixels.
     * @return the builder for chaining.
     */
    fun borderWidth(width: Float): RoundedTransformationBuilder {
        mBorderWidth = width
        return this
    }

    /**
     * Set the border width in density independent pixels.
     *
     * @param width border width in density independent pixels.
     * @return the builder for chaining.
     */
    fun borderWidthDp(width: Float): RoundedTransformationBuilder {
        mBorderWidth = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, width, mDisplayMetrics)
        return this
    }

    /**
     * Set the border color.
     *
     * @param color the color to set.
     * @return the builder for chaining.
     */
    fun borderColor(color: Int): RoundedTransformationBuilder {
        mBorderColor = ColorStateList.valueOf(color)
        return this
    }

    /**
     * Set the border color as a [ColorStateList].
     *
     * @param colors the [ColorStateList] to set.
     * @return the builder for chaining.
     */
    fun borderColor(colors: ColorStateList): RoundedTransformationBuilder {
        mBorderColor = colors
        return this
    }

    /**
     * Sets whether the image should be oval or not.
     *
     * @param oval if the image should be oval.
     * @return the builder for chaining.
     */
    fun oval(oval: Boolean): RoundedTransformationBuilder {
        mOval = oval
        return this
    }

    /**
     * Creates a [Transformation] for use with picasso.
     *
     * @return the [Transformation]
     */
    fun build(): Transformation {
        return object : Transformation {
            override fun transform(source: Bitmap): Bitmap? {
                val transformed = RoundedDrawable.fromBitmap(source)!!
                        .setScaleType(mScaleType)
                        .setCornerRadius(mCornerRadii[0], mCornerRadii[1], mCornerRadii[2], mCornerRadii[3])
                        .setBorderWidth(mBorderWidth)
                        .setBorderColor(mBorderColor)
                        .setOval(mOval)
                        .toBitmap()
                if (source != transformed) {
                    source.recycle()
                }
                return transformed
            }

            override fun key(): String {
                return ("r:" + Arrays.toString(mCornerRadii)
                        + "b:" + mBorderWidth
                        + "c:" + mBorderColor
                        + "o:" + mOval)
            }
        }
    }
}
