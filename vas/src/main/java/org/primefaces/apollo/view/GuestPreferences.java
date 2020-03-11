/*
 * Copyright 2009-2014 PrimeTek.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.primefaces.apollo.view;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class GuestPreferences implements Serializable {
    
    private String color = "vas";
    
    private boolean dark = false;
        
    private String layoutMode = "horizontal";

	public void updateTheme(String color, boolean dark) {
		this.color = color;
        this.dark = dark;
	}
    
    public String getTheme() {
        return this.color + "-" + (this.dark ? "dark" : "light");
    }
    
    public void changeScheme() {
        this.dark = !this.dark;
        this.updateTheme(this.color, this.dark);
    }
        
    public String getLayoutMode() {
        return this.layoutMode;
    }
    
    public void setLayoutMode(String value) {
        this.layoutMode = value;
    }
    
    public boolean isDark() {
        return this.dark;
    }
    
    public void setDark(boolean value) {
        this.dark = dark;
    }
}
