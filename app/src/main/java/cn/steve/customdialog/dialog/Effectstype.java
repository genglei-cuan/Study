package cn.steve.customdialog.dialog;

import cn.steve.customdialog.dialog.effects.BaseEffects;
import cn.steve.customdialog.dialog.effects.FadeIn;
import cn.steve.customdialog.dialog.effects.Fall;
import cn.steve.customdialog.dialog.effects.FlipH;
import cn.steve.customdialog.dialog.effects.FlipV;
import cn.steve.customdialog.dialog.effects.NewsPaper;
import cn.steve.customdialog.dialog.effects.RotateBottom;
import cn.steve.customdialog.dialog.effects.RotateLeft;
import cn.steve.customdialog.dialog.effects.Shake;
import cn.steve.customdialog.dialog.effects.SideFall;
import cn.steve.customdialog.dialog.effects.SlideBottom;
import cn.steve.customdialog.dialog.effects.SlideLeft;
import cn.steve.customdialog.dialog.effects.SlideRight;
import cn.steve.customdialog.dialog.effects.SlideTop;
import cn.steve.customdialog.dialog.effects.Slit;


/**
 * Created by lee on 2014/7/30.
 */
public enum Effectstype {

  Fadein(FadeIn.class), Slideleft(SlideLeft.class), Slidetop(SlideTop.class), SlideBottom(
      SlideBottom.class), Slideright(SlideRight.class), Fall(Fall.class), Newspager(NewsPaper.class), Fliph(
      FlipH.class), Flipv(FlipV.class), RotateBottom(RotateBottom.class), RotateLeft(
      RotateLeft.class), Slit(Slit.class), Shake(Shake.class), Sidefill(SideFall.class);
  private Class<? extends BaseEffects> effectsClazz;

  private Effectstype(Class<? extends BaseEffects> mclass) {
    effectsClazz = mclass;
  }

  public BaseEffects getAnimator() {
    BaseEffects bEffects = null;
    try {
      bEffects = effectsClazz.newInstance();
    } catch (ClassCastException e) {
      throw new Error("Can not init animatorClazz instance");
    } catch (InstantiationException e) {
      // TODO Auto-generated catch block
      throw new Error("Can not init animatorClazz instance");
    } catch (IllegalAccessException e) {
      // TODO Auto-generated catch block
      throw new Error("Can not init animatorClazz instance");
    }
    return bEffects;
  }
}
