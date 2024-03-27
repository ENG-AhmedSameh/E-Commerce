<%@ page import="java.util.List" %>
<%@ page import="com.google.gson.Gson" %>
<%@ page import="com.example.ecommerce.model.DTO.OrderDto" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Orders Panel</title>
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
            <h2 class="headline">Orders</h2>

            <button type="button" id="backBtn"  class="btn btn-secondary mr-1" data-toggle="modal">
                Back
            </button>

            <table class="table table-striped">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>User-ID</th>
                    <th>Created Time</th>
                    <th>Price</th>
                </tr>
                </thead>
                <tbody id="orderTableBody">
                <% List<OrderDto> orders = (List<OrderDto>) request.getSession().getAttribute("ordersPanel");
                    if (orders != null && !orders.isEmpty()) {
                        for (OrderDto order : orders) { %>
                <tr>
                    <td><%= order.getId() %></td>
                    <td><%= order.getUser().getId() %></td>
                    <td><%= order.getCreateTime() %></td>
                    <td><%= order.getPrice() %></td>
                    <td>
                        <button class="btn btn-primary btn-edit" data-order-id="<%= order.getId() %>">View</button>
                    </td>
                </tr>
                <% }
                } else { %>
                <tr>
                    <td colspan="13">No Orders exist.</td>
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
<div class="modal fade" id="editOrderModal" tabindex="-1" role="dialog" aria-labelledby="editOrderModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" style="color: black;" id="editOrderModalLabel">Edit Order</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form id="editOrderForm">
                    <input type="hidden" id="editOrderId">
                    <div class="form-group">
                        <label for="editUserID">User-ID:</label>
                        <input type="number" class="form-control" id="editUserID">
                    </div>
                    <div class="form-group">
                        <label for="editCreatedTime">Created-Time:</label>
                        <input type="date" class="form-control" id="editCreatedTime">
                    </div>
                    <div class="form-group">
                        <label for="editPrice">Price:</label>
                        <input type="number" class="form-control" id="editPrice">
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
            var orderId = $(this).data('order-id');

            // Fetch order data from the table
            var userID = $(this).closest('tr').find('td:eq(1)').text();
            var createdTime = $(this).closest('tr').find('td:eq(2)').text();
            var price = $(this).closest('tr').find('td:eq(3)').text();

            // Set values in the edit modal
            $('#editOrderId').val(orderId);
            $('#editUserID').val(userID);
            $('#editCreatedTime').val(createdTime);
            $('#editPrice').val(price);

            $('#editOrderModal').modal('show');
        });
    });
</script>

</body>
</html>

