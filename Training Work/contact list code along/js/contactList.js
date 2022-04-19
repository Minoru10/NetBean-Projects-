// JavaScript source code
$(document).ready(function () {
    loadContacts();
    addContact();
    updateContact();
});
//Load contacts from server to the page
function loadContacts() {

    clearContactTable();
    var contentRows = $('#contentRows');
    $.ajax({
        type: 'GET',
        url: 'http://contactlist.us-east-1.elasticbeanstalk.com/contacts',

        success: function (contactArray) {

            $.each(contactArray, function (index, contact) {
                var name = contact.firstName + ' ' + contact.lastName;
                var company = contact.company;
                var contactId = contact.contactId;

                var row = '<tr>';
                row += '<td>' + name + '</td>';
                row += '<td>' + company + '</td>';
                row += '<td><button type="button" class="btn btn-info" onclick="showEditForm(' + contactId + ')">Edit</button></td>';
                row += '<td><button type="button" class="btn btn-danger" onclick="deleteContact(' + contactId + ')">Delete</button></td>';
                
                row += '</tr>';

                contentRows.append(row);
            })

        },
        error: function () {

            $('#errorMessages')
                .append($('<li>')
                    .attr({ class: 'list-group-item list-group-item-danger' })
                    .text('Error calling web service. Please try again later.'));

        }
    })

}
// Add a new contanct from the page to the server resource
function addContact() {
    $('#addButton').click(function (event) {

        var haveValidationErrors = checkAndDisplayValidationErrors($('#addForm').find('input'));
        if (haveValidationErrors) {
            return false;
        }

        $.ajax({
            type: 'POST',
            url: 'http://contactlist.us-east-1.elasticbeanstalk.com/contact',
            data: JSON.stringify({
                firstName: $('#addFirstName').val(),
                lastName: $('#addLastName').val(),
                company: $('#addCompany').val(),
                phone: $('#addPhone').val(),
                email: $('#addEmail').val()
            }),
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            'dataType': 'json',
            success: function () {
                $('#errorMessages').empty();
                $('#addFirstName').val('');
                $('#addLastName').val('');
                $('#addCompany').val('');
                $('#addphone').val('');
                $('#addEmail').val('');
                loadContacts();
            },
            error: function () {
                $('#errorMessages')
                    .append($('<li>')
                        .attr({ class: 'list-group-item list-group-item-danger' })
                        .text('Error calling web service. Please try again later.'));
            }
        })
    });
}
//clear the current page of contancts before loading a new set onto the page
function clearContactTable() {
    $('#contentRows').empty();
}
//show or hide the edid forms accordingly
function showEditForm() {
    $('#errorMessages').empty();

    $('#contactTableDiv').hide();
    $('#editFormDiv').show();
}
function hideEditForm() {
    $('#errorMessages').empty();

    $('#editFirstName').val('');
    $('#editLastName').val('');
    $('#editCompany').val('');
    $('#editPhone').val('');
    $('#editEmail').val('');

    $('#contactTableDiv').show();
    $('#editFormDiv').hide();
}
//Populate the edit contact fields with the selected contact you want to edit based on its ID
function showEditForm(contactId) {
    $('#errorMessages').empty();

    $.ajax({
        type: 'GET',
        url: 'http://contactlist.us-east-1.elasticbeanstalk.com/contact/' + contactId,
        success: function (data, status) {
            $('#editFirstName').val(data.firstName);
            $('#editLastName').val(data.lastName);
            $('#editCompany').val(data.company);
            $('#editPhone').val(data.phone);
            $('#editEmail').val(data.email);
            $('#editContactId').val(data.contactId);

        },
        error: function () {
            $('#errorMessages')
                .append($('<li>')
                    .attr({ class: 'list-group-item list-group-item-danger' })
                    .text('Error calling web service. Please try again later.'));
        }
    })

    $('#contactTableDiv').hide();
    $('#editFormDiv').show();
}
// Update an existing contact
function updateContact(contactId) {
    $('#updateButton').click(function (event) {

        var haveValidationErrors = checkAndDisplayValidationErrors($('#editForm').find('input'));
        if (haveValidationErrors) {
            return false;
        }

        $.ajax({
            type: 'PUT',
            url: 'http://contactlist.us-east-1.elasticbeanstalk.com/contact/' + $('#editContactId').val(),
            data: JSON.stringify({
                contactId: $('#editContactId').val(),
                firstName: $('#editFirstName').val(),
                lastName: $('#editLastName').val(),
                company: $('#editCompany').val(),
                phone: $('#editPhone').val(),
                email: $('#editEmail').val()
            }),
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            'dataType': 'json',
            'success': function () {
                $('#errorMessage').empty();
                hideEditForm();
                loadContacts();
            },
            'error': function () {
                $('#errorMessages')
                    .append($('<li>')
                        .attr({ class: 'list-group-item list-group-item-danger' })
                        .text('Error calling web service. Please try again later.'));
            }
        })
    })
}
//Delete a contact fromt the resource list
function deleteContact(contactId) {
    $.ajax({
        type: 'DELETE',
        url: 'http://contactlist.us-east-1.elasticbeanstalk.com/contact/' + contactId,
        success: function () {
            loadContacts();
        }
    });
}
//Check for valid user input
function checkAndDisplayValidationErrors(input) {
    $('#errorMessages').empty();

    var errorMessages = [];

    input.each(function () {
        if (!this.validity.valid) {
            var errorField = $('label[for=' + this.id + ']').text();
            errorMessages.push(errorField + ' ' + this.validationMessage);
        }
    });

    if (errorMessages.length > 0) {
        $.each(errorMessages, function (index, message) {
            $('#errorMessages').append($('<li>').attr({ class: 'list-group-item list-group-item-danger' }).text(message));
        });
        // return true, indicating that there were errors
        return true;
    } else {
        // return false, indicating that there were no errors
        return false;
    }
}