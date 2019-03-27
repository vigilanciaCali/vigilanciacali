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
import co.edu.usbcali.vas.model.dto.VideoDocumentDTO;
import co.edu.usbcali.vas.presentation.businessDelegate.*;
import co.edu.usbcali.vas.utilities.*;
/**
 * @author Zathura Code Generator http://zathuracode.org/
 * www.zathuracode.org
 * 
 */
 

@ManagedBean
@ViewScoped
public class VideoDocumentView implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private static final Logger log = LoggerFactory.getLogger(VideoDocumentView.class);

public VideoDocumentView() {
super();
}
    private InputText txtCreatedBy;
    private InputText txtDescription;
    private InputText txtMimeType;
    private InputText txtSource;
    private InputText txtUpdatedBy;
    private InputText txtId_Video;
    private InputText txtId;
            private Calendar txtCreatedAt;
            private Calendar txtUpdatedAt;
    private CommandButton btnSave;
private CommandButton btnModify;
private CommandButton btnDelete;
private CommandButton btnClear;
private List<VideoDocumentDTO> data;
private VideoDocumentDTO selectedVideoDocument;
private VideoDocument entity;
private boolean showDialog;
    
@ManagedProperty(value="#{BusinessDelegatorView}")
private IBusinessDelegatorView businessDelegatorView;


	      public void rowEventListener(RowEditEvent e){
			try {
			
			VideoDocumentDTO videoDocumentDTO = (VideoDocumentDTO) e.getObject(); 
			
							if(txtCreatedBy == null){
					txtCreatedBy = new InputText ();
				}
				txtCreatedBy.setValue(videoDocumentDTO.getCreatedBy());
								if(txtDescription == null){
					txtDescription = new InputText ();
				}
				txtDescription.setValue(videoDocumentDTO.getDescription());
								if(txtMimeType == null){
					txtMimeType = new InputText ();
				}
				txtMimeType.setValue(videoDocumentDTO.getMimeType());
								if(txtSource == null){
					txtSource = new InputText ();
				}
				txtSource.setValue(videoDocumentDTO.getSource());
								if(txtUpdatedBy == null){
					txtUpdatedBy = new InputText ();
				}
				txtUpdatedBy.setValue(videoDocumentDTO.getUpdatedBy());
								if(txtId_Video == null){
					txtId_Video = new InputText ();
				}
				txtId_Video.setValue(videoDocumentDTO.getId_Video());
											if(txtId == null){
					txtId = new InputText ();
				}
				txtId.setValue(videoDocumentDTO.getId());
														if(txtCreatedAt == null){
					txtCreatedAt = new Calendar ();
				}
				txtCreatedAt.setValue(videoDocumentDTO.getCreatedAt());
								if(txtUpdatedAt == null){
					txtUpdatedAt = new Calendar ();
				}
				txtUpdatedAt.setValue(videoDocumentDTO.getUpdatedAt());
										
						
					        Long id = FacesUtils.checkLong(txtId);
		    			entity = businessDelegatorView.getVideoDocument(id);
			
			action_modify();
			
			}catch (Exception ex) {
			
			}
		
		}
		
	public String action_new(){
		action_clear();
		selectedVideoDocument = null;
		setShowDialog(true);
		return "";
	}

	public String action_clear() {
		
		entity = null;
		selectedVideoDocument = null;
		
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
                    if(txtSource != null){
			 txtSource.setValue(null);
             txtSource.setDisabled(true);
			}
                    if(txtUpdatedBy != null){
			 txtUpdatedBy.setValue(null);
             txtUpdatedBy.setDisabled(true);
			}
                    if(txtId_Video != null){
			 txtId_Video.setValue(null);
             txtId_Video.setDisabled(true);
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
	    	    	entity = id != null ? businessDelegatorView.getVideoDocument(id) : null;
	    } catch (Exception e) {
	    	entity = null;
	    }
	    
		if(entity==null){
	    	        txtCreatedBy.setDisabled(false);
	    	        txtDescription.setDisabled(false);
	    	        txtMimeType.setDisabled(false);
	    	        txtSource.setDisabled(false);
	    	        txtUpdatedBy.setDisabled(false);
	    	        txtId_Video.setDisabled(false);
	    	    	        	            txtCreatedAt.setDisabled(false);
	        	            txtUpdatedAt.setDisabled(false);
	        	    	    	        txtId.setDisabled(false);
	    	    		    btnSave.setDisabled(false);
		    }else{
		    		        txtCreatedAt.setValue(entity.getCreatedAt());txtCreatedAt.setDisabled(false);
		    		        txtCreatedBy.setValue(entity.getCreatedBy());txtCreatedBy.setDisabled(false);
		    		        txtDescription.setValue(entity.getDescription());txtDescription.setDisabled(false);
		    		        txtMimeType.setValue(entity.getMimeType());txtMimeType.setDisabled(false);
		    		        txtSource.setValue(entity.getSource());txtSource.setDisabled(false);
		    		        txtUpdatedAt.setValue(entity.getUpdatedAt());txtUpdatedAt.setDisabled(false);
		    		        txtUpdatedBy.setValue(entity.getUpdatedBy());txtUpdatedBy.setDisabled(false);
		    		        txtId_Video.setValue(entity.getVideo().getId());txtId_Video.setDisabled(false);
		    		    		        txtId.setValue(entity.getId());txtId.setDisabled(true);
		    		    btnSave.setDisabled(false);
		    if(btnDelete!=null){
		    	btnDelete.setDisabled(false);
		    }
	    }
    }
        
	public String action_edit(ActionEvent evt){
    	
    	selectedVideoDocument = (VideoDocumentDTO)(evt.getComponent().getAttributes().get("selectedVideoDocument"));		
            txtCreatedAt.setValue(selectedVideoDocument.getCreatedAt());txtCreatedAt.setDisabled(false);
            txtCreatedBy.setValue(selectedVideoDocument.getCreatedBy());txtCreatedBy.setDisabled(false);
            txtDescription.setValue(selectedVideoDocument.getDescription());txtDescription.setDisabled(false);
            txtMimeType.setValue(selectedVideoDocument.getMimeType());txtMimeType.setDisabled(false);
            txtSource.setValue(selectedVideoDocument.getSource());txtSource.setDisabled(false);
            txtUpdatedAt.setValue(selectedVideoDocument.getUpdatedAt());txtUpdatedAt.setDisabled(false);
            txtUpdatedBy.setValue(selectedVideoDocument.getUpdatedBy());txtUpdatedBy.setDisabled(false);
            txtId_Video.setValue(selectedVideoDocument.getId_Video());txtId_Video.setDisabled(false);
                txtId.setValue(selectedVideoDocument.getId());txtId.setDisabled(true);
            btnSave.setDisabled(false);
    	setShowDialog(true);

    	return "";
    }
    
    public String action_save(){    	
        try {        	
        	if(selectedVideoDocument == null && entity==null){
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
        	entity = new VideoDocument();

	    	        Long id = FacesUtils.checkLong(txtId);
	    
                        entity.setCreatedAt(FacesUtils.checkDate(txtCreatedAt));
                                entity.setCreatedBy(FacesUtils.checkString(txtCreatedBy));
                                entity.setDescription(FacesUtils.checkString(txtDescription));
                                            	            		entity.setId(id);
            	                                            entity.setMimeType(FacesUtils.checkString(txtMimeType));
        
                                entity.setUpdatedAt(FacesUtils.checkDate(txtUpdatedAt));
                                entity.setUpdatedBy(FacesUtils.checkString(txtUpdatedBy));
                            entity.setVideo(FacesUtils.checkLong(txtId_Video) != null ? businessDelegatorView.getVideo(FacesUtils.checkLong(txtId_Video)) : null );
        	        businessDelegatorView.saveVideoDocument(entity);
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
		    		        Long id = new Long(selectedVideoDocument.getId());
		    	    		entity = businessDelegatorView.getVideoDocument(id);
    		}
    		
        	    		entity.setCreatedAt(FacesUtils.checkDate(txtCreatedAt));
    	        	    		entity.setCreatedBy(FacesUtils.checkString(txtCreatedBy));
    	        	    		entity.setDescription(FacesUtils.checkString(txtDescription));
    	        	        	    		entity.setMimeType(FacesUtils.checkString(txtMimeType));
    	        	    
    	        	    		entity.setUpdatedAt(FacesUtils.checkDate(txtUpdatedAt));
    	        	    		entity.setUpdatedBy(FacesUtils.checkString(txtUpdatedBy));
    	    	    	        entity.setVideo(FacesUtils.checkLong(txtId_Video) != null ? businessDelegatorView.getVideo(FacesUtils.checkLong(txtId_Video)) : null );
	        	        businessDelegatorView.updateVideoDocument(entity);
			FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
		   data=null;
           FacesUtils.addErrorMessage(e.getMessage());
        }
        return "";
	}
	
	public String action_delete_datatable(ActionEvent evt){
		try{
        	selectedVideoDocument = (VideoDocumentDTO)(evt.getComponent().getAttributes().get("selectedVideoDocument"));
    						Long id = new Long(selectedVideoDocument.getId());
						entity = businessDelegatorView.getVideoDocument(id);
        	action_delete();
        }catch(Exception e ){
		 FacesUtils.addErrorMessage(e.getMessage());
		}    
        return "";
    }
	
	public String action_delete_master(){
		try{
					        Long id = FacesUtils.checkLong(txtId);
		    		    entity = businessDelegatorView.getVideoDocument(id);
        	action_delete();
        }catch(Exception e ){
		 FacesUtils.addErrorMessage(e.getMessage());
		}    
        return "";
    }
    
	public void action_delete() throws Exception{
		try{
			businessDelegatorView.deleteVideoDocument(entity);
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
			selectedVideoDocument = (VideoDocumentDTO)(evt.getComponent().getAttributes().get("selectedVideoDocument"));		
	    	        Long id = new Long(selectedVideoDocument.getId());
	        		entity = businessDelegatorView.getVideoDocument(id);
			businessDelegatorView.deleteVideoDocument(entity);
			data.remove(selectedVideoDocument);
    		FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
			action_clear();
		}catch(Exception e ){
		 FacesUtils.addErrorMessage(e.getMessage());
		}  
		
		return "";
	}
		
        public String action_modifyWitDTO(Date createdAt, String createdBy, String description, Long id, String mimeType, Date updatedAt, String updatedBy, Long id_Video) throws Exception {
        try {
        
        	    		entity.setCreatedAt(FacesUtils.checkDate(createdAt));
    	        	    		entity.setCreatedBy(FacesUtils.checkString(createdBy));
    	        	    		entity.setDescription(FacesUtils.checkString(description));
    	        	        	    		entity.setMimeType(FacesUtils.checkString(mimeType));
    	        	    		entity.setUpdatedAt(FacesUtils.checkDate(updatedAt));
    	        	    		entity.setUpdatedBy(FacesUtils.checkString(updatedBy));
    	            businessDelegatorView.updateVideoDocument(entity);
		FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
        //renderManager.getOnDemandRenderer("VideoDocumentView").requestRender();
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
                                                                                            public InputText getTxtSource() {
                                                return txtSource;
                                                }
                                                public void setTxtSource(InputText txtSource) {
                                                this.txtSource = txtSource;
                                                }
                                                                                            public InputText getTxtUpdatedBy() {
                                                return txtUpdatedBy;
                                                }
                                                public void setTxtUpdatedBy(InputText txtUpdatedBy) {
                                                this.txtUpdatedBy = txtUpdatedBy;
                                                }
                                                                                            public InputText getTxtId_Video() {
                                                return txtId_Video;
                                                }
                                                public void setTxtId_Video(InputText txtId_Video) {
                                                this.txtId_Video = txtId_Video;
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
                                                                                        											
																						public List<VideoDocumentDTO> getData() {
												try{
													if(data==null){
													data = businessDelegatorView.getDataVideoDocument();
													}	
												
												}catch(Exception e){
												 e.printStackTrace();
												}
												return data;
											}
																						public void setData(List<VideoDocumentDTO> videoDocumentDTO){
												this.data=videoDocumentDTO;
											}
											
																						
											public VideoDocumentDTO getSelectedVideoDocument(){
												return selectedVideoDocument;
											}
											
											public void setSelectedVideoDocument (VideoDocumentDTO videoDocument ){
												this.selectedVideoDocument = videoDocument;
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
