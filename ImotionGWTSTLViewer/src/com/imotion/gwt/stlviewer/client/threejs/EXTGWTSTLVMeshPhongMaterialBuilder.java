/*
 * gwt-wrap three.js
 * 
 * Copyright (c) 2011 aki@akjava.com

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 
 
 based Three.js r45
 https://github.com/mrdoob/three.js
 The MIT License

Copyright (c) 2010-2011 three.js Authors. All rights reserved.

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
  
 */
package com.imotion.gwt.stlviewer.client.threejs;

import com.akjava.gwt.three.client.materials.Material;
import com.google.gwt.core.client.JavaScriptObject;


public class EXTGWTSTLVMeshPhongMaterialBuilder extends JavaScriptObject {
	
	protected EXTGWTSTLVMeshPhongMaterialBuilder() {}
	
	public final static EXTGWTSTLVMeshPhongMaterialBuilder create() {
		return (EXTGWTSTLVMeshPhongMaterialBuilder) EXTGWTSTLVMeshPhongMaterialBuilder.createObject();
	}
	
	public final static EXTGWTSTLVMeshPhongMaterialBuilder create(int color) {
		EXTGWTSTLVMeshPhongMaterialBuilder builder= (EXTGWTSTLVMeshPhongMaterialBuilder) EXTGWTSTLVMeshPhongMaterialBuilder.createObject();
		return builder.color(color);
	}
	
	public final EXTGWTSTLVMeshPhongMaterialBuilder color(int r,int g,int b) {
		int c = (0xff & r)<<16 | (0xff & g)<<8 | (0xff & b);
		return color(c);
	}
	
	public final  EXTGWTSTLVMeshPhongMaterialBuilder color(double c) {
		return color((int) c);
	}
	
	public final native EXTGWTSTLVMeshPhongMaterialBuilder color(int c)/*-{
	this["color"]=c;
	return this;
	}-*/;
	
	public final native EXTGWTSTLVMeshPhongMaterialBuilder specular(int s)/*-{
		this["specular"] = s;
		return this;
	}-*/;
	
	public final native EXTGWTSTLVMeshPhongMaterialBuilder ambient(int a)/*-{
		this["ambient"] = a;
		return this;
	}-*/;
	
	public final native EXTGWTSTLVMeshPhongMaterialBuilder shininess(int s)/*-{
		this["shininess"] = s;
		return this;
	}-*/;
	
	public final native EXTGWTSTLVMeshPhongMaterialBuilder reflectivity(double r)/*-{
		this["reflectivity"] = r;
		return this;
	}-*/;
	
	public final Material build() {
		return build(this);
	}
	
	private final native Material build(JavaScriptObject object)/*-{
		return new $wnd.THREE.MeshPhongMaterial(object);
	}-*/;
	
	
}
