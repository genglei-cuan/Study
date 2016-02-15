将toolbar当中actionbar用的时候，如果自定义view的话，会有左右留白的现象。
`java
    Toolbar parent = (Toolbar) this.getParent();//first get parent toolbar of current action bar
    parent.setContentInsetsAbsolute(0, 0);// set padding programmatically to 0dp
`