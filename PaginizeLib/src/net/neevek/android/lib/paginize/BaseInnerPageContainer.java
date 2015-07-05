package net.neevek.android.lib.paginize;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import net.neevek.android.lib.paginize.annotation.InnerPageContainerLayoutResId;
import net.neevek.android.lib.paginize.exception.InjectFailedException;

/**
 * Copyright (c) 2015 neevek <i@neevek.net>
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:

 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.

 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

/**
 * An BaseInnerPageContainer is a Page that is used to hold & manage InnerPages
 * Known subclasses are: InnerPageContainer, ViewPagerPage
 *
 * @see InnerPageManager
 * @see InnerPage
 */
public abstract class BaseInnerPageContainer extends Page {
  private ViewGroup mVgContainer;

  public BaseInnerPageContainer(PageActivity pageActivity) {
    super(pageActivity);

    Class clazz = getClass();

    InnerPageContainerLayoutResId resIdAnnotation = null;

    try {
      do {
        if (clazz.isAnnotationPresent(InnerPageContainerLayoutResId.class)) {
          resIdAnnotation = (InnerPageContainerLayoutResId) clazz.getAnnotation(InnerPageContainerLayoutResId.class);
          break;
        }
      } while ((clazz = clazz.getSuperclass()) != BaseInnerPageContainer.class);

    } catch (Exception e) {
      e.printStackTrace();
      throw new InjectFailedException(e);
    }

    if (resIdAnnotation == null) {
      throw new IllegalStateException("Must specify a layout for InnerPageContainer with the @InnerPageContainerLayoutResId annotation.");
    }

    View container = getView().findViewById(resIdAnnotation.value());
    if (container == null) {
      throw new IllegalStateException("Can not find the layout with the specified resource ID: " + resIdAnnotation.value());
    }
    if (!(container instanceof ViewGroup)) {
      throw new IllegalStateException("The specified layout for InnerPageContainer is not of type ViewGroup.");
    }

    mVgContainer = (ViewGroup)container;
  }

  public ViewGroup getContainerView() {
    return mVgContainer;
  }
}
