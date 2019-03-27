package  co.edu.usbcali.vas.presentation.backingBeans;
import java.util.ArrayList;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;
import java.io.Serializable;
import java.sql.*;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.calendar.*;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.event.RowEditEvent;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import  co.edu.usbcali.vas.exceptions.*;
import co.edu.usbcali.vas.model.*;
import co.edu.usbcali.vas.model.dto.NewsDTO;
import co.edu.usbcali.vas.presentation.businessDelegate.*;
import co.edu.usbcali.vas.utilities.*;
/**
 * @author Zathura Code Generator http://zathuracode.org/
 * www.zathuracode.org
 * 
 */
 

@ManagedBean
@ViewScoped
public class NewsView implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private static final Logger log = LoggerFactory.getLogger(NewsView.class);

public NewsView() {
super();
}
    private InputText txtCreatedBy;
    private InputText txtDescription;
    private InputText txtMimeType;
    private InputText txtNewsInfo;
    private InputText txtSource;
    private InputText txtSrc;
    private InputText txtUpdatedBy;
    private InputText txtUrl;
    private InputText txtId;
            private Calendar txtCreatedAt;
            private Calendar txtUpdatedAt;
    private CommandButton btnSave;
private CommandButton btnModify;
private CommandButton btnDelete;
private CommandButton btnClear;
private List<NewsDTO> data;
private NewsDTO selectedNews;
private News entity;
private boolean showDialog;
    
@ManagedProperty(value="#{BusinessDelegatorView}")
private IBusinessDelegatorView businessDelegatorView;


	      public void rowEventListener(RowEditEvent e){
			try {
			
			NewsDTO newsDTO = (NewsDTO) e.getObject(); 
			
							if(txtCreatedBy == null){
					txtCreatedBy = new InputText ();
				}
				txtCreatedBy.setValue(newsDTO.getCreatedBy());
								if(txtDescription == null){
					txtDescription = new InputText ();
				}
				txtDescription.setValue(newsDTO.getDescription());
								if(txtMimeType == null){
					txtMimeType = new InputText ();
				}
				txtMimeType.setValue(newsDTO.getMimeType());
								if(txtNewsInfo == null){
					txtNewsInfo = new InputText ();
				}
				txtNewsInfo.setValue(newsDTO.getNewsInfo());
								if(txtSource == null){
					txtSource = new InputText ();
				}
				txtSource.setValue(newsDTO.getSource());
								if(txtSrc == null){
					txtSrc = new InputText ();
				}
				txtSrc.setValue(newsDTO.getSrc());
								if(txtUpdatedBy == null){
					txtUpdatedBy = new InputText ();
				}
				txtUpdatedBy.setValue(newsDTO.getUpdatedBy());
								if(txtUrl == null){
					txtUrl = new InputText ();
				}
				txtUrl.setValue(newsDTO.getUrl());
											if(txtId == null){
					txtId = new InputText ();
				}
				txtId.setValue(newsDTO.getId());
														if(txtCreatedAt == null){
					txtCreatedAt = new Calendar ();
				}
				txtCreatedAt.setValue(newsDTO.getCreatedAt());
								if(txtUpdatedAt == null){
					txtUpdatedAt = new Calendar ();
				}
				txtUpdatedAt.setValue(newsDTO.getUpdatedAt());
										
						
					        Long id = FacesUtils.checkLong(txtId);
		    			entity = businessDelegatorView.getNews(id);
			
			action_modify();
			
			}catch (Exception ex) {
			
			}
		
		}
		
	public String action_new(){
		action_clear();
		selectedNews = null;
		setShowDialog(true);
		return "";
	}

	public String action_clear() {
		
		entity = null;
		selectedNews = null;
		
                    if(txtCreatedBy != null){
			 txtCreatedBy.setValue(null);
             txtCreatedBy.setDisabled(true);
			}
                    if(txtDescription != null){
			 txtDescription.setValue(null);
             txtDescription.setDisabled(true);
			}
                    if(txtMimeType != null){
			 txtMimeType.setValue(null);
             txtMimeType.setDisabled(true);
			}
                    if(txtNewsInfo != null){
			 txtNewsInfo.setValue(null);
             txtNewsInfo.setDisabled(true);
			}
                    if(txtSource != null){
			 txtSource.setValue(null);
             txtSource.setDisabled(true);
			}
                    if(txtSrc != null){
			 txtSrc.setValue(null);
             txtSrc.setDisabled(true);
			}
                    if(txtUpdatedBy != null){
			 txtUpdatedBy.setValue(null);
             txtUpdatedBy.setDisabled(true);
			}
                    if(txtUrl != null){
			 txtUrl.setValue(null);
             txtUrl.setDisabled(true);
			}
                                            if(txtCreatedAt != null){
				  txtCreatedAt.setValue(null);
                  txtCreatedAt.setDisabled(true);
				}
                            if(txtUpdatedAt != null){
				  txtUpdatedAt.setValue(null);
                  txtUpdatedAt.setDisabled(true);
				}
                            			    if(txtId != null){
				   txtId.setValue(null);
				   txtId.setDisabled(false);	
				}
                        if(btnSave != null){
		  btnSave.setDisabled(true);
		}
		if (btnDelete != null) {
        	btnDelete.setDisabled(true);
        }
        return "";
        }

														public void listener_txtCreatedAt(){
			Date inputDate = (Date)txtCreatedAt.getValue();
			DateFormat dateFormat= new SimpleDateFormat("dd/MM/yyyy");
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage("Selected Date " +dateFormat.format(inputDate)));
			}
						public void listener_txtUpdatedAt(){
			Date inputDate = (Date)txtUpdatedAt.getValue();
			DateFormat dateFormat= new SimpleDateFormat("dd/MM/yyyy");
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage("Selected Date " +dateFormat.format(inputDate)));
			}
							
	public void listener_txtId(){
    
	    try {
	    	        Long id = FacesUtils.checkLong(txtId);
	    	    	entity = id != null ? businessDelegatorView.getNews(id) : null;
	    } catch (Exception e) {
	    	entity = null;
	    }
	    
		if(entity==null){
	    	        txtCreatedBy.setDisabled(false);
	    	        txtDescription.setDisabled(false);
	    	        txtMimeType.setDisabled(false);
	    	        txtNewsInfo.setDisabled(false);
	    	        txtSource.setDisabled(false);
	    	        txtSrc.setDisabled(false);
	    	        txtUpdatedBy.setDisabled(false);
	    	        txtUrl.setDisabled(false);
	    	    	        	            txtCreatedAt.setDisabled(false);
	        	            txtUpdatedAt.setDisabled(false);
	        	    	    	        txtId.setDisabled(false);
	    	    		    btnSave.setDisabled(false);
		    }else{
		    		        txtCreatedAt.setValue(entity.getCreatedAt());txtCreatedAt.setDisabled(false);
		    		        txtCreatedBy.setValue(entity.getCreatedBy());txtCreatedBy.setDisabled(false);
		    		        txtDescription.setValue(entity.getDescription());txtDescription.setDisabled(false);
		    		        txtMimeType.setValue(entity.getMimeType());txtMimeType.setDisabled(false);
		    		        txtNewsInfo.setValue(entity.getNewsInfo());txtNewsInfo.setDisabled(false);
		    		        txtSource.setValue(entity.getSource());txtSource.setDisabled(false);
		    		        txtSrc.setValue(entity.getSrc());txtSrc.setDisabled(false);
		    		        txtUpdatedAt.setValue(entity.getUpdatedAt());txtUpdatedAt.setDisabled(false);
		    		        txtUpdatedBy.setValue(entity.getUpdatedBy());txtUpdatedBy.setDisabled(false);
		    		        txtUrl.setValue(entity.getUrl());txtUrl.setDisabled(false);
		    		    		        txtId.setValue(entity.getId());txtId.setDisabled(true);
		    		    btnSave.setDisabled(false);
		    if(btnDelete!=null){
		    	btnDelete.setDisabled(false);
		    }
	    }
    }
        
	public String action_edit(ActionEvent evt){
    	
    	selectedNews = (NewsDTO)(evt.getComponent().getAttributes().get("selectedNews"));		
            txtCreatedAt.setValue(selectedNews.getCreatedAt());txtCreatedAt.setDisabled(false);
            txtCreatedBy.setValue(selectedNews.getCreatedBy());txtCreatedBy.setDisabled(false);
            txtDescription.setValue(selectedNews.getDescription());txtDescription.setDisabled(false);
            txtMimeType.setValue(selectedNews.getMimeType());txtMimeType.setDisabled(false);
            txtNewsInfo.setValue(selectedNews.getNewsInfo());txtNewsInfo.setDisabled(false);
            txtSource.setValue(selectedNews.getSource());txtSource.setDisabled(false);
            txtSrc.setValue(selectedNews.getSrc());txtSrc.setDisabled(false);
            txtUpdatedAt.setValue(selectedNews.getUpdatedAt());txtUpdatedAt.setDisabled(false);
            txtUpdatedBy.setValue(selectedNews.getUpdatedBy());txtUpdatedBy.setDisabled(false);
            txtUrl.setValue(selectedNews.getUrl());txtUrl.setDisabled(false);
                txtId.setValue(selectedNews.getId());txtId.setDisabled(true);
            btnSave.setDisabled(false);
    	setShowDialog(true);

    	return "";
    }
    
    public String action_save(){    	
        try {        	
        	if(selectedNews == null && entity==null){
        		action_create();
        	}else{
        		action_modify();
        	}
        	data = null;
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    	
    }
    
    public String action_create() {
        try {
        	entity = new News();

	    	        Long id = FacesUtils.checkLong(txtId);
	    
                        entity.setCreatedAt(FacesUtils.checkDate(txtCreatedAt));
                                entity.setCreatedBy(FacesUtils.checkString(txtCreatedBy));
                                entity.setDescription(FacesUtils.checkString(txtDescription));
                                            	            		entity.setId(id);
            	                                            entity.setMimeType(FacesUtils.checkString(txtMimeType));
                                entity.setNewsInfo(FacesUtils.checkString(txtNewsInfo));

                                entity.setSrc(FacesUtils.checkString(txtSrc));
                                entity.setUpdatedAt(FacesUtils.checkDate(txtUpdatedAt));
                                entity.setUpdatedBy(FacesUtils.checkString(txtUpdatedBy));
                                entity.setUrl(FacesUtils.checkString(txtUrl));
                        	        businessDelegatorView.saveNews(entity);
	        FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYSAVED);
			action_clear();
        } catch (Exception e) {
        	entity = null;
        	FacesUtils.addErrorMessage(e.getMessage());
        }
        return "";
    }
		
	public String action_modify() {
        try {
        	if(entity==null){
		    		        Long id = new Long(selectedNews.getId());
		    	    		entity = businessDelegatorView.getNews(id);
    		}
    		
        	    		entity.setCreatedAt(FacesUtils.checkDate(txtCreatedAt));
    	        	    		entity.setCreatedBy(FacesUtils.checkString(txtCreatedBy));
    	        	    		entity.setDescription(FacesUtils.checkString(txtDescription));
    	        	        	    		entity.setMimeType(FacesUtils.checkString(txtMimeType));
    	        	    		entity.setNewsInfo(FacesUtils.checkString(txtNewsInfo));
    	  
    	        	    		entity.setSrc(FacesUtils.checkString(txtSrc));
    	        	    		entity.setUpdatedAt(FacesUtils.checkDate(txtUpdatedAt));
    	        	    		entity.setUpdatedBy(FacesUtils.checkString(txtUpdatedBy));
    	        	    		entity.setUrl(FacesUtils.checkString(txtUrl));
    	    	        	        businessDelegatorView.updateNews(entity);
			FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
		   data=null;
           FacesUtils.addErrorMessage(e.getMessage());
        }
        return "";
	}
	
	public String action_delete_datatable(ActionEvent evt){
		try{
        	selectedNews = (NewsDTO)(evt.getComponent().getAttributes().get("selectedNews"));
    						Long id = new Long(selectedNews.getId());
						entity = businessDelegatorView.getNews(id);
        	action_delete();
        }catch(Exception e ){
		 FacesUtils.addErrorMessage(e.getMessage());
		}    
        return "";
    }
	
	public String action_delete_master(){
		try{
					        Long id = FacesUtils.checkLong(txtId);
		    		    entity = businessDelegatorView.getNews(id);
        	action_delete();
        }catch(Exception e ){
		 FacesUtils.addErrorMessage(e.getMessage());
		}    
        return "";
    }
    
	public void action_delete() throws Exception{
		try{
			businessDelegatorView.deleteNews(entity);
    		FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
			action_clear();
            data= null;
		}catch(Exception e ){
			throw e;
		}  
	}	
	
    public String action_closeDialog(){
    	setShowDialog(false);
    	action_clear();    	
    	return "";
    }	
		
		
				
	public String actionDeleteDataTableEditable(ActionEvent evt){
		
		try{
			selectedNews = (NewsDTO)(evt.getComponent().getAttributes().get("selectedNews"));		
	    	        Long id = new Long(selectedNews.getId());
	        		entity = businessDelegatorView.getNews(id);
			businessDelegatorView.deleteNews(entity);
			data.remove(selectedNews);
    		FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
			action_clear();
		}catch(Exception e ){
		 FacesUtils.addErrorMessage(e.getMessage());
		}  
		
		return "";
	}
		
        public String action_modifyWitDTO(Date createdAt, String createdBy, String description, Long id, String mimeType, String newsInfo, String src, Date updatedAt, String updatedBy, String url) throws Exception {
        try {
        
        	    		entity.setCreatedAt(FacesUtils.checkDate(createdAt));
    	        	    		entity.setCreatedBy(FacesUtils.checkString(createdBy));
    	        	    		entity.setDescription(FacesUtils.checkString(description));
    	        	        	    		entity.setMimeType(FacesUtils.checkString(mimeType));
    	        	    		entity.setNewsInfo(FacesUtils.checkString(newsInfo));

    	        	    		entity.setSrc(FacesUtils.checkString(src));
    	        	    		entity.setUpdatedAt(FacesUtils.checkDate(updatedAt));
    	        	    		entity.setUpdatedBy(FacesUtils.checkString(updatedBy));
    	        	    		entity.setUrl(FacesUtils.checkString(url));
    	            businessDelegatorView.updateNews(entity);
		FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
        //renderManager.getOnDemandRenderer("NewsView").requestRender();
        FacesUtils.addErrorMessage(e.getMessage());
        throw e;
        }
        return "";
        }
								
									
                                          
                                                                                            public InputText getTxtCreatedBy() {
                                                return txtCreatedBy;
                                                }
                                                public void setTxtCreatedBy(InputText txtCreatedBy) {
                                                this.txtCreatedBy = txtCreatedBy;
                                                }
                                                                                            public InputText getTxtDescription() {
                                                return txtDescription;
                                                }
                                                public void setTxtDescription(InputText txtDescription) {
                                                this.txtDescription = txtDescription;
                                                }
                                                                                            public InputText getTxtMimeType() {
                                                return txtMimeType;
                                                }
                                                public void setTxtMimeType(InputText txtMimeType) {
                                                this.txtMimeType = txtMimeType;
                                                }
                                                                                            public InputText getTxtNewsInfo() {
                                                return txtNewsInfo;
                                                }
                                                public void setTxtNewsInfo(InputText txtNewsInfo) {
                                                this.txtNewsInfo = txtNewsInfo;
                                                }
                                                                                            public InputText getTxtSource() {
                                                return txtSource;
                                                }
                                                public void setTxtSource(InputText txtSource) {
                                                this.txtSource = txtSource;
                                                }
                                                                                            public InputText getTxtSrc() {
                                                return txtSrc;
                                                }
                                                public void setTxtSrc(InputText txtSrc) {
                                                this.txtSrc = txtSrc;
                                                }
                                                                                            public InputText getTxtUpdatedBy() {
                                                return txtUpdatedBy;
                                                }
                                                public void setTxtUpdatedBy(InputText txtUpdatedBy) {
                                                this.txtUpdatedBy = txtUpdatedBy;
                                                }
                                                                                            public InputText getTxtUrl() {
                                                return txtUrl;
                                                }
                                                public void setTxtUrl(InputText txtUrl) {
                                                this.txtUrl = txtUrl;
                                                }
                                                                                                                                                                                            public Calendar getTxtCreatedAt() {
                                                    return txtCreatedAt;
                                                    }
                                                    public void setTxtCreatedAt(Calendar txtCreatedAt) {
                                                    this.txtCreatedAt = txtCreatedAt;
                                                    }
                                                                                                    public Calendar getTxtUpdatedAt() {
                                                    return txtUpdatedAt;
                                                    }
                                                    public void setTxtUpdatedAt(Calendar txtUpdatedAt) {
                                                    this.txtUpdatedAt = txtUpdatedAt;
                                                    }
                                                                                                                                                                                        public InputText getTxtId() {
                                                return txtId;
                                                }
                                                public void setTxtId(InputText txtId) {
                                                this.txtId = txtId;
                                                }
                                                                                        											
																						public List<NewsDTO> getData() {
												try{
													if(data==null){
													data = businessDelegatorView.getDataNews();
													}	
												
												}catch(Exception e){
												 e.printStackTrace();
												}
												return data;
											}
																						public void setData(List<NewsDTO> newsDTO){
												this.data=newsDTO;
											}
											
																						
											public NewsDTO getSelectedNews(){
												return selectedNews;
											}
											
											public void setSelectedNews (NewsDTO news ){
												this.selectedNews = news;
											}
											
											
                                            public CommandButton getBtnSave() {
                                            return btnSave;
                                            }
                                            public void setBtnSave(CommandButton btnSave) {
                                            this.btnSave = btnSave;
                                            }
                                            public CommandButton getBtnModify() {
                                            return btnModify;
                                            }
                                            public void setBtnModify(CommandButton btnModify) {
                                            this.btnModify = btnModify;
                                            }
                                            public CommandButton getBtnDelete() {
                                            return btnDelete;
                                            }
                                            public void setBtnDelete(CommandButton btnDelete) {
                                            this.btnDelete = btnDelete;
                                            }
                                            public CommandButton getBtnClear() {
                                            return btnClear;
                                            }
                                            public void setBtnClear(CommandButton btnClear) {
                                            this.btnClear = btnClear;
                                            }
                                            
                                            public TimeZone getTimeZone() {
                                             return java.util.TimeZone.getDefault();
                                             }
																							
											 public IBusinessDelegatorView getBusinessDelegatorView() {
											 return businessDelegatorView;
											 }

											public void setBusinessDelegatorView(IBusinessDelegatorView businessDelegatorView) {
											this.businessDelegatorView = businessDelegatorView;
											}
											
											public boolean isShowDialog() {
												return showDialog;
											}
										
											public void setShowDialog(boolean showDialog) {
												this.showDialog = showDialog;
											}											
                                             
									}
