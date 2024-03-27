<%@ page import="java.util.List" %>
<%@ page import="com.google.gson.Gson" %>
<%@ page import="com.example.ecommerce.model.DTO.UserDto" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Customers Panel</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="assets/css/pro.css">
    <style>
        
label {
  color: black;
  text-align: center;
}

a {
  text-decoration: none;
  display: inline-block;
  padding: 8px 16px;
}

a:hover {
  background-color: #ddd;
  color: black;
}

.previous {
  background-color: #f1f1f1;
  color: black;
}

.next {
  background-color: #04AA6D;
  color: white;
}

.round {
  border-radius: 50%;
}
    </style>
</head>
<body>


<div class="container mt-5">
    <div class="row">
        <div class="col-md-12">
            <h2 class="headline">Customers</h2>

            <button type="button" id="backBtn"  class="btn btn-secondary mr-1" data-toggle="modal">
                Back
            </button>

            <table class="table table-striped">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Username</th>
                    <th>Email</th>
                    <th>Phone Number</th>
                    <th>Job</th>
                    <th>Credit Limit</th>
                    <th>City</th>
                    <th>Street</th>
                    <th>Gender</th>
                    <th>Actions</th>
                </tr>
                </thead>
                <tbody id="userTableBody">
                <% List<UserDto> users = (List<UserDto>) request.getSession().getAttribute("customersPanel");
                    if (users != null && !users.isEmpty()) {
                        for (UserDto user : users) { %>
                <tr>
                    <td><%= user.getId() %></td>
                    <td><%= user.getFirstName() %></td>
                    <td><%= user.getLastName() %></td>
                    <td><%= user.getUserName() %></td>
                    <td><%= user.getEmail() %></td>
                    <td><%= user.getPhoneNumber() %></td>
                    <td><%= user.getJob() %></td>
                    <td><%= user.getCreditLimit() %></td>
                    <td><%= user.getCity() %></td>
                    <td><%= user.getStreet() %></td>
                    <td><%= user.getGender() %></td>
                    <td>
                        <button class="btn btn-primary btn-edit" data-user-id="<%= user.getId() %>">View</button>
                    </td>
                </tr>
                <% }
                } else { %>
                <tr>
                    <td colspan="13">No Customers exist.</td>
                </tr>
                <% } %>
                </tbody>
            </table>
            <a href="#" class="previous" >&laquo; Previous</a>
            <a href="#" class="next" >Next &raquo;</a>
        </div>
    </div>
</div>

<!-- Edit modal -->
<div class="modal fade" id="editUserModal" tabindex="-1" role="dialog" aria-labelledby="editUserModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" style="color: black;" id="editUserModalLabel">Edit User</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form id="editUserForm">
                    <input type="hidden" id="editUserId">
                    <div class="form-group">
                        <label for="editFirstName">First Name:</label>
                        <input  readonly ="text" class="form-control" id="editFirstName">
                    </div>
                    <div class="form-group">
                        <label for="editLastName">Last Name:</label>
                        <input readonly type="text" class="form-control" id="editLastName">
                    </div>
                    <div class="form-group">
                        <label for="editUserName">Username:</label>
                        <input readonly  type="text" class="form-control" id="editUserName">
                    </div>
                    <div class="form-group">
                        <label for="editEmail">Email:</label>
                        <input readonly  type="email" class="form-control" id="editEmail">
                    </div>
                    <div class="form-group">
                        <label for="editPhone">Phone:</label>
                        <input  readonly ="tel" class="form-control" id="editPhone">
                    </div>
                    <div class="form-group">
                        <label for="editJob">Job:</label>
                        <input readonly ="text" class="form-control" id="editJob">
                    </div>
                    <div class="form-group">
                        <label for="editCreditLimit">Credit Limit:</label>
                        <input readonly  type="number" class="form-control" id="editCreditLimit">
                    </div>
                    <div class="form-group">
                        <label for="editCity">City:</label>
                        <input readonly type="text" class="form-control" id="editCity">
                    </div>
                    <div class="form-group">
                        <label for="editStreet">Street:</label>
                        <input readonly  type="text" class="form-control" id="editStreet">
                    </div>
                    <div class="form-group">
                        <label for="editGender">Gender:</label>
                        <input readonly  type="text" class="form-control" id="editGender">
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>

            </div>
        </div>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<script>
    $(document).ready(function () {
        $('.btn-edit').click(function () {
            var userId = $(this).data('user-id');

            // Fetch user data from the table
            var firstName = $(this).closest('tr').find('td:eq(1)').text();
            var lastName = $(this).closest('tr').find('td:eq(2)').text();
            var userName = $(this).closest('tr').find('td:eq(3)').text();
            var email = $(this).closest('tr').find('td:eq(4)').text();
            var phoneNumber = $(this).closest('tr').find('td:eq(5)').text();
            var job = $(this).closest('tr').find('td:eq(6)').text();
            var creditLimit = $(this).closest('tr').find('td:eq(7)').text();
            var city = $(this).closest('tr').find('td:eq(8)').text();
            var street = $(this).closest('tr').find('td:eq(9)').text();
            var gender = $(this).closest('tr').find('td:eq(10)').text();

            // Set values in the edit modal
            $('#editUserId').val(userId);
            $('#editFirstName').val(firstName);
            $('#editLastName').val(lastName);
            $('#editUserName').val(userName);
            $('#editEmail').val(email);
            $('#editPhone').val(phoneNumber);
            $('#editJob').val(job);
            $('#editCreditLimit').val(creditLimit);
            $('#editCity').val(city);
            $('#editStreet').val(street);
            $('#editGender').val(gender);

            $('#editUserModal').modal('show');
        });
    });
</script>

</body>
</html>

