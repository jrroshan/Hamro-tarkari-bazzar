<%-- 
    Document   : adminsignup
    Created on : Nov 26, 2019, 10:04:44 PM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="header.jsp" />

<jsp:include page="sidebar.jsp" />

<!-- BEGIN PAGE -->      
      <div id="main-content">
         <!-- BEGIN PAGE CONTAINER-->
         <div class="container-fluid">
            <!-- BEGIN PAGE CONTENT-->
             <div class="row-fluid">
                 <div class="span12">
                     <!-- BEGIN VALIDATION STATES-->
                     <div class="widget green">
                         <div class="widget-title">
                             <h4><i class=" icon-user"></i> Add User </h4>
                         </div>
                         <div class="widget-body form">
                             <!-- BEGIN FORM-->
                             <form id="validate_form" action="register" method="POST" class="cmxform form-horizontal" enctype="multipart/form-data"> 
                              <div class="control-group ">
                                  <label class="control-label">Image</label>
                                  <div class="controls">
                                    <input class="span6" type="file" name="file" data-parsley-fileextension='jpg.png' data-parsley-trigger="keyup" onchange="previewFile()" required  />
                                  </div>
                              </div>
                              <div class="form-group">
                                  <label class="control-label">View Image</label>
                              </div> 
                              <div class="form-group">
                                  <div class="controls">
                                      <img class="img-thumbnail" src="assets/admin/images/profile-pic.jpg" alt="img" >
                                  </div>
                              </div> 
                              <br>
                              <div class="control-group ">
                                  <label for="firstname" class="control-label">FirstName</label>
                                  <div class="controls ">
                                    <input type="text" name="fname" id="first_name" placeholder="First Name" required data-parsley-pattern="^[a-zA-Z]+$" data-parsley-trigger="keyup" class="span6" />
                                  </div>
                              </div>
                              <div class="control-group ">
                                  <label for="lastname" class="control-label">LastName</label>
                                  <div class="controls ">
                                    <input type="text" name="lname" id="last_name" placeholder="Last Name" required data-parsley-pattern="^[a-zA-Z]+$" data-parsley-trigger="keyup" class="span6" />
                                  </div>
                              </div>
                              <div class="control-group ">
                                  <label for="username" class="control-label">Username</label>
                                  <div class="controls ">
                                    <input type="text" name="username" id="username" placeholder="Username" required data-parsley-pattern="^[a-zA-Z0-9]+$" data-parsley-trigger="keyup" class="span6" />
                                  </div>
                              </div>
                              <div class="control-group ">
                                  <label for="password" class="control-label">Password</label>
                                  <div class="controls ">
                                    <input type="password" name="password" id="password" placeholder="Password" required data-parsley-length="[8, 16]" data-parsley-trigger="keyup" class="span6" />
                                  </div>
                              </div>
                              <div class="control-group ">
                                  <label for="confirmpassword" class="control-label">Confirm Password</label>
                                  <div class="controls ">
                                    <input type="password"  id="confirm_password" placeholder="Confirm Password" data-parsley-equalto="#password" data-parsley-trigger="keyup" required class="span6" />
                                  </div>
                              </div>
                              <div class="control-group ">
                                    <label for="address" class="control-label">Address</label>
                                    <div class="controls">
                                        <input type="text" name="address" id="address" placeholder="Address" required data-parsley-pattern="^[a-zA-Z]+$" data-parsley-trigger="keyup" class="span6" />
                                    </div>
                                </div>
                                <div class="control-group ">
                                    <label for="contact" class="control-label">Contact</label>
                                    <div class="controls">
                                        <input type="text" name="phone" id="contact" placeholder="Contact Number" required data-parsley-pattern="^[0-9]+$" data-parsley-trigger="keyup" class="span6" />
                                    </div>
                                </div>
                                <div class="control-group">
                                    <label class="control-label">Gender</label>
                                    <div class="controls"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                       <label class="radio">
                                            <input type="radio" name="gender" value="male" checked />
                                            Male
                                        </label>
                                        <label class="radio">
                                            <input type="radio" name="gender" value="female"  />
                                           Female
                                        </label>
                                    </div>   
                                </div>
                              <div class="control-group ">
                                    <label for="contact" class="control-label">City</label>
                                    <div class="controls">
                                        <input type="text" name="city" id="contact" placeholder="City" required data-parsley-pattern="^[a-zA-Z]+$" data-parsley-trigger="keyup" class="span6" />
                                    </div>
                                </div>
                              <div class="control-group ">
                                    <label for="contact" class="control-label">State</label>
                                    <div class="controls">
                                        <input type="text" name="state" id="contact" placeholder="State" required data-parsley-pattern="^[a-zA-Z]+$" data-parsley-trigger="keyup" class="span6" />
                                    </div>
                                </div>
                              <div class="control-group ">
                                    <label for="contact" class="control-label">Country</label>
                                    <div class="controls">
                                        <input type="text" name="country" id="contact" placeholder="Country" required data-parsley-pattern="^[a-zA-Z]+$" data-parsley-trigger="keyup" class="span6" />
                                    </div>
                                </div>
<!--                                <div class="control-group">
                                  <label class="control-label">Status</label>
                                  <div class="controls">
                                      <select class="span6 " name="status" data-placeholder="Choose a Category" >
                                          <option value="active" selected>Active</option>
                                          <option value="inactive">Inactive</option>
                                      </select>
                                  </div>
                                </div>-->
                                <div class="form-actions">
                                    <button class="btn btn-success" name="btnsubmit" type="submit">Save</button>
                                    <button class="btn" type="button">Cancel</button>
                                </div>
                            </form>
                            <!-- END FORM-->                             
                         </div>
                     </div>
                     <!-- END VALIDATION STATES-->
                 </div>
             </div>           

            <!-- END PAGE CONTENT-->

         </div>
         <!-- END PAGE CONTAINER-->
      </div>
      <!-- END PAGE -->
   </div>
   <!-- END CONTAINER -->

   <!-- BEGIN FOOTER -->
   <div id="footer">
       &copy; Karuna Hospital Pvt. Ltd.
   </div>
   <!-- END FOOTER -->

   <!-- BEGIN JAVASCRIPTS -->
   <!-- Load javascripts at bottom, this will reduce page load time -->
   <link rel="stylesheet" type="text/css" href="assets/admin/css/validate.css">
   <script src="assets/admin/js/jquery-1.8.3.min.js"></script>
   <script src="assets/admin/js/jquery.nicescroll.js" type="text/javascript"></script>
   <script src="assets/admin/plugins/bootstrap/js/bootstrap.min.js"></script>
   <script src="assets/admin/plugins/bootstrap-wizard/jquery.bootstrap.wizard.min.js"></script>
   <script src="assets/admin/js/jquery.blockui.js"></script>
   <!-- ie8 fixes -->
   <!--[if lt IE 9]>
   <script src="js/excanvas.js"></script>
   <script src="js/respond.js"></script>
   <![endif]-->
   <script type="text/javascript" src="assets/admin/js/jquery.validate.min.js"></script>
   <script type="text/javascript" src="assets/admin/js/additional-methods.min.js"></script>
   <script type="text/javascript" src="assets/admin/plugins/chosen-bootstrap/chosen/chosen.jquery.min.js"></script>
   <script type="text/javascript" src="assets/admin/plugins/uniform/jquery.uniform.min.js"></script>
   <script src="assets/admin/js/jquery.scrollTo.min.js"></script>
   <script src="assets/admin/js/parsley.js"></script>


   <!--common script for all pages-->
   <script src="assets/admin/js/common-scripts.js"></script>
   <!--script for this page-->
   <script src="assets/admin/js/form-validation-script.js"></script>   

   <!-- END JAVASCRIPTS -->

</body>
<!-- END BODY -->
</html>

<script type="text/javascript">  
$(document).ready(function(){  
    $('#validate_form').parsley(); 
   window.ParsleyValidator
          .addValidator('fileextension', function (value, requirement) {
              var tagslistarr = requirement.split(',');
              var fileExtension = value.split('.').pop();
              var arr=[];
              $.each(tagslistarr,function(i,val){
                 arr.push(val);
              });
              }, 32)
          .addMessage('en', 'fileextension', 'Image should be in jpg or png!!!');

});
    function previewFile()
  {
    var preview = document.querySelector("img.img-thumbnail");

    var file = document.querySelector('input[type=file]').files[0];

    var reader = new FileReader();

    reader.addEventListener("load", function () {

    preview.src = reader.result;

    }, false);

    if (file)

    {

    reader.readAsDataURL(file);

    }
  }
</script>