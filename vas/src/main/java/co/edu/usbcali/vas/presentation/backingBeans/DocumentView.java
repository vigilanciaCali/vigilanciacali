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
import co.edu.usbcali.vas.model.dto.DocumentDTO;
import co.edu.usbcali.vas.presentation.businessDelegate.*;
import co.edu.usbcali.vas.utilities.*;
/**
 * @author Zathura Code Generator http://zathuracode.org/
 * www.zathuracode.org
 * 
 */
 

@ManagedBean
@ViewScoped
public class DocumentView implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private static final Logger log = LoggerFactory.getLogger(DocumentView.class);

public DocumentView() {
super();
}
    private InputText txtCreatedBy;
    private InputText txtDescription;
    private InputText txtMimeType;
    private InputText txtSource;
    private InputText txtUpdatedBy;
    private InputText txtId_Users;
    private InputText txtId;
            private Calendar txtCreatedAt;
            private Calendar txtUpdatedAt;
    private CommandButton btnSave;
private CommandButton btnModify;
private CommandButton btnDelete;
private CommandButton btnClear;
private List<DocumentDTO> data;
private DocumentDTO selectedDocument;
private Document entity;
private boolean showDialog;
    
@ManagedProperty(value="#{BusinessDelegatorView}")
private IBusinessDelegatorView businessDelegatorView;


	      public void rowEventListener(RowEditEvent e){
			try {
			
			DocumentDTO documentDTO = (DocumentDTO) e.getObject(); 
			
							if(txtCreatedBy == null){
					txtCreatedBy = new InputText ();
				}
				txtCreatedBy.setValue(documentDTO.getCreatedBy());
								if(txtDescription == null){
					txtDescription = new InputText ();
				}
				txtDescription.setValue(documentDTO.getDescription());
								if(txtMimeType == null){
					txtMimeType = new InputText ();
				}
				txtMimeType.setValue(documentDTO.getMimeType());
								if(txtSource == null){
					txtSource = new InputText ();
				}
				txtSource.setValue(documentDTO.getSource());
								if(txtUpdatedBy == null){
					txtUpdatedBy = new InputText ();
				}
				txtUpdatedBy.setValue(documentDTO.getUpdatedBy());
								if(txtId_Users == null){
					txtId_Users = new InputText ();
				}
				txtId_Users.setValue(documentDTO.getId_Users());
											if(txtId == null){
					txtId = new InputText ();
				}
				txtId.setValue(documentDTO.getId());
														if(txtCreatedAt == null){
					txtCreatedAt = new Calendar ();
				}
				txtCreatedAt.setValue(documentDTO.getCreatedAt());
								if(txtUpdatedAt == null){
					txtUpdatedAt = new Calendar ();
				}
				txtUpdatedAt.setValue(documentDTO.getUpdatedAt());
										
						
					        Long id = FacesUtils.checkLong(txtId);
		    			entity = businessDelegatorView.getDocument(id);
			
			action_modify();
			
			}catch (Exception ex) {
			
			}
		
		}
		
	public String action_new(){
		action_clear();
		selectedDocument = null;
		setShowDialog(true);
		return "";
	}

	public String action_clear() {
		
		entity = null;
		selectedDocument = null;
		
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
                    if(txtId_Users != null){
			 txtId_Users.setValue(null);
             txtId_Users.setDisabled(true);
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
	    	    	entity = id != null ? businessDelegatorView.getDocument(id) : null;
	    } catch (Exception e) {
	    	entity = null;
	    }
	    
		if(entity==null){
	    	        txtCreatedBy.setDisabled(false);
	    	        txtDescription.setDisabled(false);
	    	        txtMimeType.setDisabled(false);
	    	        txtSource.setDisabled(false);
	    	        txtUpdatedBy.setDisabled(false);
	    	        txtId_Users.setDisabled(false);
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
		    		        txtId_Users.setValue(entity.getUsers().getId());txtId_Users.setDisabled(false);
		    		    		        txtId.setValue(entity.getId());txtId.setDisabled(true);
		    		    btnSave.setDisabled(false);
		    if(btnDelete!=null){
		    	btnDelete.setDisabled(false);
		    }
	    }
    }
        
	public String action_edit(ActionEvent evt){
    	
    	selectedDocument = (DocumentDTO)(evt.getComponent().getAttributes().get("selectedDocument"));		
            txtCreatedAt.setValue(selectedDocument.getCreatedAt());txtCreatedAt.setDisabled(false);
            txtCreatedBy.setValue(selectedDocument.getCreatedBy());txtCreatedBy.setDisabled(false);
            txtDescription.setValue(selectedDocument.getDescription());txtDescription.setDisabled(false);
            txtMimeType.setValue(selectedDocument.getMimeType());txtMimeType.setDisabled(false);
            txtSource.setValue(selectedDocument.getSource());txtSource.setDisabled(false);
            txtUpdatedAt.setValue(selectedDocument.getUpdatedAt());txtUpdatedAt.setDisabled(false);
            txtUpdatedBy.setValue(selectedDocument.getUpdatedBy());txtUpdatedBy.setDisabled(false);
            txtId_Users.setValue(selectedDocument.getId_Users());txtId_Users.setDisabled(false);
                txtId.setValue(selectedDocument.getId());txtId.setDisabled(true);
            btnSave.setDisabled(false);
    	setShowDialog(true);

    	return "";
    }
    
    public String action_save(){    	
        try {        	
        	if(selectedDocument == null && entity==null){
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
        	entity = new Document();

	    	        Long id = FacesUtils.checkLong(txtId);
	    
                        entity.setCreatedAt(FacesUtils.checkDate(txtCreatedAt));
                                entity.setCreatedBy(FacesUtils.checkString(txtCreatedBy));
                                entity.setDescription(FacesUtils.checkString(txtDescription));
                                            	            		entity.setId(id);
            	                                            entity.setMimeType(FacesUtils.checkString(txtMimeType));
                    
                                entity.setUpdatedAt(FacesUtils.checkDate(txtUpdatedAt));
                                entity.setUpdatedBy(FacesUtils.checkString(txtUpdatedBy));
                            entity.setUsers(FacesUtils.checkInteger(txtId_Users) != null ? businessDelegatorView.getUsers(FacesUtils.checkInteger(txtId_Users)) : null );
        	        businessDelegatorView.saveDocument(entity);
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
		    		        Long id = new Long(selectedDocument.getId());
		    	    		entity = businessDelegatorView.getDocument(id);
    		}
    		
        	    		entity.setCreatedAt(FacesUtils.checkDate(txtCreatedAt));
    	        	    		entity.setCreatedBy(FacesUtils.checkString(txtCreatedBy));
    	        	    		entity.setDescription(FacesUtils.checkString(txtDescription));
    	        	        	    		entity.setMimeType(FacesUtils.checkString(txtMimeType));

    	        	    		entity.setUpdatedAt(FacesUtils.checkDate(txtUpdatedAt));
    	        	    		entity.setUpdatedBy(FacesUtils.checkString(txtUpdatedBy));
    	    	    	        entity.setUsers(FacesUtils.checkInteger(txtId_Users) != null ? businessDelegatorView.getUsers(FacesUtils.checkInteger(txtId_Users)) : null );
	        	        businessDelegatorView.updateDocument(entity);
			FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
		   data=null;
           FacesUtils.addErrorMessage(e.getMessage());
        }
        return "";
	}
	
	public String action_delete_datatable(ActionEvent evt){
		try{
        	selectedDocument = (DocumentDTO)(evt.getComponent().getAttributes().get("selectedDocument"));
    						Long id = new Long(selectedDocument.getId());
						entity = businessDelegatorView.getDocument(id);
        	action_delete();
        }catch(Exception e ){
		 FacesUtils.addErrorMessage(e.getMessage());
		}    
        return "";
    }
	
	public String action_delete_master(){
		try{
					        Long id = FacesUtils.checkLong(txtId);
		    		    entity = businessDelegatorView.getDocument(id);
        	action_delete();
        }catch(Exception e ){
		 FacesUtils.addErrorMessage(e.getMessage());
		}    
        return "";
    }
    
	public void action_delete() throws Exception{
		try{
			businessDelegatorView.deleteDocument(entity);
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
			selectedDocument = (DocumentDTO)(evt.getComponent().getAttributes().get("selectedDocument"));		
	    	        Long id = new Long(selectedDocument.getId());
	        		entity = businessDelegatorView.getDocument(id);
			businessDelegatorView.deleteDocument(entity);
			data.remove(selectedDocument);
    		FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
			action_clear();
		}catch(Exception e ){
		 FacesUtils.addErrorMessage(e.getMessage());
		}  
		
		return "";
	}
		
        public String action_modifyWitDTO(Date createdAt, String createdBy, String description, Long id, String mimeType, Date updatedAt, String updatedBy, Integer id_Users) throws Exception {
        try {
        
        	    		entity.setCreatedAt(FacesUtils.checkDate(createdAt));
    	        	    		entity.setCreatedBy(FacesUtils.checkString(createdBy));
    	        	    		entity.setDescription(FacesUtils.checkString(description));
    	        	        	    		entity.setMimeType(FacesUtils.checkString(mimeType));
    	        	    		entity.setUpdatedAt(FacesUtils.checkDate(updatedAt));
    	        	    		entity.setUpdatedBy(FacesUtils.checkString(updatedBy));
    	            businessDelegatorView.updateDocument(entity);
		FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
        //renderManager.getOnDemandRenderer("DocumentView").requestRender();
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
                                                                                            public InputText getTxtId_Users() {
                                                return txtId_Users;
                                                }
                                                public void setTxtId_Users(InputText txtId_Users) {
                                                this.txtId_Users = txtId_Users;
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
                                                                                        											
																						public List<DocumentDTO> getData() {
												try{
													if(data==null){
													data = businessDelegatorView.getDataDocument();
													}	
												
												}catch(Exception e){
												 e.printStackTrace();
												}
												return data;
											}
																						public void setData(List<DocumentDTO> documentDTO){
												this.data=documentDTO;
											}
											
																						
											public DocumentDTO getSelectedDocument(){
												return selectedDocument;
											}
											
											public void setSelectedDocument (DocumentDTO document ){
												this.selectedDocument = document;
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
