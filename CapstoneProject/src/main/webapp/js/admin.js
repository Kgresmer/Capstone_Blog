$(document).ready(function () {
    loadPosts();
    displayNumberOfPosts();
    displayActiveTags();
    loadUsers();

    $('#activeAcct').change(function () {
        cb = $(this);
        cb.val(cb.prop('checked'));
    });
    $('#adminAcct').change(function () {
        cb = $(this);
        cb.val(cb.prop('checked'));
    });

    //create new user
    $('#createUser').click(function (event) {
        event.preventDefault();
        var pass1 = document.getElementById("add-password").value;
        var pass2 = document.getElementById("confirm-password").value;
        if (pass1 != pass2) {
            alert("Passwords don't match!!");
            document.getElementById("add-password").style.borderColor = "#E34234";
            document.getElementById("confirm-password").style.borderColor = "#E34234";
        }
        else {
            $.ajax({
                type: 'POST',
                url: 'user',
                data: JSON.stringify({
                    userID: $('#edituserID').val(),
                    username: $('#add-user-name').val(),
                    password: $('#add-password').val(),
                    active: $('#activeAcct').val(),
                    admin: $('#adminAcct').val()
                }),
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json; charset=utf-8'
                },
                'dataType': 'json'
            }).success(function (data, status) {

                loadUsers();

                $('#add-user-name').val(""),
                        $('#add-password').val(""),
                        $('#activeAcct').val(""),
                        $('#adminAcct').val("");
                $('#confirm-password').val("");
                console.log("success");
                $('#validationErrors2').empty();
            }).error(function (data, status) {
                console.log("error");
                $('#validationErrors2').empty();
                $.each(data.responseJSON.fieldErrors, function (index,
                        validationError) {
                    var errorDiv = $('#validationErrors2');
                    errorDiv.append(validationError.message);
                });
                console.log("error");
            });
        }
    });

    // add a blogpost and update a blog post if it already exists
    $('#add-button').click(function (event) {
        event.preventDefault();
        var expdate = "";
        var postdate = "";
        if ($('#expiration-date').val() == "") {
            expdate = "9999-12-12";
        }
        else {
            expdate = $('#expiration-date').val();
        }

        if ($('#post-date').val() == "") {
            postdate = new Date().toJSON().slice(0, 10).toString();
        }
        else {
            postdate = $('#post-date').val();
        }
        if (0 != $('#editPostID').val()) {
            $.ajax({
                type: 'PUT',
                url: 'post/' + $('#editPostID').val(),
                data: JSON.stringify({
                    postID: $('#editPostID').val(),
                    title: $('#title').val(),
                    author: $('#author').val(),
                    content: tinyMCE.activeEditor.getContent({format: 'raw'}),
                    tags: $('#tags').val(),
                    expirationDate: expdate,
                    postDate: postdate
                }),
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                'dataType': 'json'
            }).success(function () {
                $('#title').val(""),
                        $('#author').val(""),
                        tinyMCE.activeEditor.setContent(""),
                        $('#tags').val(""),
                        $('#expiration-date').val(""),
                        $('#editPostID').val("0"),
                        $('#editPostDate').val("");
                $('#post-date').val("");
                loadPosts();
                $('#validationErrors').empty();
            }).error(function (data, status) {
                console.log('error');
                $('#validationErrors').empty();
                $.each(data.responseJSON.fieldErrors, function (index,
                        validationError) {
                    var errorDiv = $('#validationErrors');
                    errorDiv.append(validationError.message).append($('<br>'));
                });

            });
        } else {
            $.ajax({
                type: 'POST',
                url: 'post',
                data: JSON.stringify({
                    title: $('#title').val(),
                    author: $('#author').val(),
                    content: tinyMCE.activeEditor.getContent({format: 'raw'}),
                    tags: $('#tags').val(),
                    expirationDate: expdate,
                    postDate: postdate
                }),
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json; charset=utf-8'
                },
                'dataType': 'json'
            }).success(function (data, status) {
                $('#title').val(""),
                        $('#author').val(""),
                        tinyMCE.activeEditor.setContent(""),
                        $('#tags').val(""),
                        $('#expiration-date').val(""),
                        $('#editPostID').val("0"),
                        $('#editPostDate').val("");
                $('#post-date').val("");
                loadPosts();
                $('#validationErrors').empty();
            }).error(function (data, status) {
                console.log("error");
                $('#validationErrors').empty();
                $.each(data.responseJSON.fieldErrors, function (index,
                        validationError) {
                    var errorDiv = $('#validationErrors');
                    errorDiv.append(validationError.message).append($('<br>'));
                });
            });
        }
    });

});

//load all posts including drafts and fill each display table

function loadPosts() {
    loadPins();
    $.ajax({
        url: "posts"
    }).success(function (data, status) {
        fillDraftSection(data, status);
        fillActivePosts(data, status);
        fillDisplaySection(data, status);
    });
}

//load all users, enabled and disabled
function loadUsers() {
    $.ajax({
        url: "users"
    }).success(function (data, status) {
        fillUsersTable(data, status);
    });
}

//load all pin posts, drafts and active ones.
function loadPins() {
    $.ajax({
        url: "pinposts"
    }).success(function (data, status) {
        fillPinDraftSection(data, status);
        fillActivePinPosts(data, status);
        fillPinDisplaySection(data, status);
    });
}


//Clears the search results and shows all posts again
$('.showall-button').click(function (event) {
    loadPosts();
});
//enter text search function for posts
$('#search-button').click(function (event) {
    event.preventDefault();
    $.ajax({
        type: 'GET',
        url: 'tag/' + $('#search-text').val()
    }).success(function (data, status) {
        if (data.length == 0) {
            alert('No search results with that keyword.');
        } else {
            fillDisplaySection(data, status);
        }
    }).error(function (data, status) {
        alert("You cannot search with a empty value.");
    });
});
//clear all input fields and validation errors in admin page for post entry
$('#clear-button').click(function (event) {
    event.preventDefault();
    $('#validationErrors').empty();
    $('#title').val("");
    $('#author').val("");
    tinyMCE.activeEditor.setContent("");
    $('#tags').val("");
    $('#expiration-date').val("");
    $('#editPostID').val("0");
    $('#editPostDate').val("");
    $('#post-date').val("");
});
//makes a post active as well as updates fields if edited. 
$('#publish-button').click(function (event) {
    event.preventDefault();
    var expdate = "";
    var postdate = "";
    if ($('#expiration-date').val() == "") {
        expdate = "9999-12-12";
    }
    else {
        expdate = $('#expiration-date').val();
    }

    if ($('#post-date').val() == "") {
        postdate = new Date().toJSON().slice(0, 10).toString();
    }
    else {
        postdate = $('#post-date').val();
    }
    $.ajax({
        type: 'PATCH',
        url: 'post/' + $('#editPostID').val(),
        data: JSON.stringify({
            postID: $('#editPostID').val(),
            title: $('#title').val(),
            author: $('#author').val(),
            content: tinyMCE.activeEditor.getContent({format: 'raw'}),
            tags: $('#tags').val(),
            postDate: postdate,
            expirationDate: expdate,
            status: 1
        }),
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json; charset=utf-8'
        },
        'dataType': 'json',
        success: function (data) {

        },
        error: function (data) {

        },
        complete: function (data) {
            loadPosts();
            $('#validationErrors').empty();
            $('#title').val(""),
                    $('#author').val(""),
                    tinyMCE.activeEditor.setContent(""),
                    $('#tags').val(""),
                    $('#expiration-date').val(""),
                    $('#editPostID').val("0"),
                    $('#editPostDate').val("");
            $('#post-date').val("");
            $.each(data.responseJSON.fieldErrors, function (index,
                    validationError) {
                var errorDiv = $('#validationErrors');
                errorDiv.append(validationError.message).append($('<br>'));
            });
        }
    });
});
//makes the post not active and puts it into drafts. Also updates fields if edited
$('#un-publish-button').click(function (event) {
    event.preventDefault();
    var expdate = "";
    var postdate = "";
    if ($('#expiration-date').val() == "") {
        expdate = "9999-12-12";
    }
    else {
        expdate = $('#expiration-date').val();
    }

    if ($('#post-date').val() == "") {
        postdate = new Date().toJSON().slice(0, 10).toString();
    }
    else {
        postdate = $('#post-date').val();
    }
    $.ajax({
        type: 'PATCH',
        url: 'post/' + $('#editPostID').val(),
        data: JSON.stringify({
            postID: $('#editPostID').val(),
            title: $('#title').val(),
            author: $('#author').val(),
            content: tinyMCE.activeEditor.getContent({format: 'raw'}),
            tags: $('#tags').val(),
            postDate: postdate,
            expirationDate: expdate,
            status: 0
        }),
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json; charset=utf-8'
        },
        'dataType': 'json',
        success: function (data) {

        },
        error: function (data) {

        },
        complete: function (data) {
            loadPosts();
            $('#title').val(""),
                    $('#author').val(""),
                    tinyMCE.activeEditor.setContent(""),
                    $('#tags').val(""),
                    $('#expiration-date').val(""),
                    $('#editPostID').val("0"),
                    $('#editPostDate').val("");
            $('#validationErrors').empty();
            $('#post-date').val("");
            $.each(data.responseJSON.fieldErrors, function (index,
                    validationError) {
                var errorDiv = $('#validationErrors');
                errorDiv.append(validationError.message).append($('<br>'));
            });
        }
    });
});
//fills the user display table in the user management.jsp
function fillUsersTable(data, status) {
    $('#userRows').empty();
    var cTable = $('#userRows');
    var active = "disabled";
    $.each(data, function (index, user) {
        if (user.active) {
            active = "enabled";
        }
        cTable.append($('<tr>')
                .append($('<td>').text(user.username))
                .append($('<td>').text(active))
                .append($('<td>')
                        .append($('<a>')
                                .attr({
                                    'data-user-id': user.userID,
                                    'data-toggle': 'modal',
                                    'data-target': '#editModal'
                                })
                                .text('Edit')
                                )
                        )
                .append($('<td>').append($('<a>').attr({'onClick': 'deleteUser(' + user.userID + ')'}).text('Delete')))
                );
    });
}

//used to hold user IDs
var globaluserID = 0;
//A modal for editing each user when the edit button is clicked
$('#editModal').on('show.bs.modal', function (event) {
    var element = $(event.relatedTarget);
    var userID = element.data('user-id');
    var modal = $(this);
    $.ajax({
        type: 'GET',
        url: 'user/' + userID
    }).success(function (user) {
        cb = $('#editactiveAcct');
        if (user.active) {
            modal.find('#editactiveAcct').val(cb.prop('checked'));
            document.getElementById("editactiveAcct").checked = true;
        } else {
            modal.find('#editactiveAcct').val();
        }
        cb = $('#editadminAcct');
        console.log(user.admin);
        $.ajax({
            type: 'GET',
            url: 'authority/' + user.username
        }).success(function (auths) {

            if (auths.length > 1) {
                modal.find('#editadminAcct').val(cb.prop('checked'));
                document.getElementById("editadminAcct").checked = true;
            } else {
                modal.find('#editadminAcct').val();
            }

        });
        globaluserID = user.userID;
        modal.find('#edituserID').val(user.userID);
        modal.find('#edit-user-name').val(user.username);
        modal.find('#edit-password').val(user.password);
    });
});
//upon button click this updates the user information
$('#updateUser').click(function (event) {
    event.preventDefault();
    $('#editactiveAcct').change(function () {
        cb = $(this);
        cb.val(cb.prop('checked'));
    });
    $('#editadminAcct').change(function () {
        cb = $(this);
        cb.val(cb.prop('checked'));
    });
    var pass1 = document.getElementById("edit-password").value;
    var pass2 = document.getElementById("confirm-password2").value;
    if (pass1 != pass2) {
        alert("Passwords don't match!!");
        document.getElementById("add-password").style.borderColor = "#E34234";
        document.getElementById("confirm-password2").style.borderColor = "#E34234";
    }

    else {
        $.ajax({
            type: 'PUT',
            url: 'user/' + globaluserID,
            data: JSON.stringify({
                userID: globaluserID,
                username: $('#edit-user-name').val(),
                password: $('#edit-password').val(),
                active: document.getElementById('editactiveAcct').checked,
                admin: document.getElementById('editadminAcct').checked
            }),
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            'dataType': 'json'
        }).success(function () {
            loadUsers();
            $('editModal').modal('hide');
            $('#edituserID').val("0");
        });
    }
});
$(document).ready(function () {
    loadPins();
    //function used to add and update pinned posts
    $('#add-button2').click(function (event) {
        event.preventDefault();
        var expdate = "";
        var postdate = "";
        if ($('#expiration-date').val() == "") {
            expdate = "9999-12-12";
        }
        else {
            expdate = $('#expiration-date').val();
        }

        if ($('#post-date').val() == "") {
            postdate = new Date().toJSON().slice(0, 10).toString();
        }
        else {
            postdate = $('#post-date').val();
        }
        if (0 != $('#editPinID').val()) {
            $.ajax({
                type: 'PUT',
                url: 'pinpost/' + $('#editPinID').val(),
                data: JSON.stringify({
                    pinPostID: $('#editPinID').val(),
                    title: $('#title').val(),
                    author: $('#author').val(),
                    content: tinyMCE.activeEditor.getContent({format: 'raw'}),
                    expirationDate: expdate,
                    postDate: postdate,
                    status: 0,
                }),
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                'dataType': 'json'
            }).success(function () {
                $('#title').val(""),
                        $('#author').val(""),
                        tinyMCE.activeEditor.setContent(""),
                        $('#expiration-date').val(""),
                        $('#editPinID').val("0"),
                        $('#editPostDate').val("");
                $('#post-date').val("");
                loadPins();
                $('#validationErrors').empty();
            }).error(function (data, status) {
                console.log('error');
                $('#validationErrors').empty();
                $.each(data.responseJSON.fieldErrors, function (index,
                        validationError) {
                    var errorDiv = $('#validationErrors');
                    errorDiv.append(validationError.message).append($('<br>'));
                });
            });
        } else {
            $.ajax({
                type: 'POST',
                url: 'pinpost',
                data: JSON.stringify({
                    title: $('#title').val(),
                    author: $('#author').val(),
                    content: tinyMCE.activeEditor.getContent({format: 'raw'}),
                    expirationDate: expdate,
                    postDate: postdate
                }),
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json; charset=utf-8'
                },
                'dataType': 'json'
            }).success(function (data, status) {
                $('#title').val(""),
                        $('#author').val(""),
                        tinyMCE.activeEditor.setContent(""),
                        $('#expiration-date').val(""),
                        $('#editPinID').val("0"),
                        $('#editPostDate').val("");
                $('#post-date').val("");
                loadPins();
                $('#validationErrors').empty();
            }).error(function (data, status) {
                console.log("error");
                $('#validationErrors').empty();
                $.each(data.responseJSON.fieldErrors, function (index,
                        validationError) {
                    var errorDiv = $('#validationErrors');
                    errorDiv.append(validationError.message).append($('<br>'));
                });
            });
        }
    });
});
//publish a pinned post and update any fields that were edited
$('#publish-button2').click(function (event) {
    event.preventDefault();
    var expdate = "";
    var postdate = "";
    if ($('#expiration-date').val() == "") {
        expdate = "9999-12-12";
    }
    else {
        expdate = $('#expiration-date').val();
    }

    if ($('#post-date').val() == "") {
        postdate = new Date().toJSON().slice(0, 10).toString();
    }
    else {
        postdate = $('#post-date').val();
    }
    $.ajax({
        type: 'PATCH',
        url: 'pinpost/' + $('#editPinID').val(),
        data: JSON.stringify({
            pinPostID: $('#editPinID').val(),
            title: $('#title').val(),
            author: $('#author').val(),
            content: tinyMCE.activeEditor.getContent({format: 'raw'}),
            postDate: postdate,
            expirationDate: expdate,
            status: 1
        }),
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json; charset=utf-8'
        },
        'dataType': 'json',
        success: function (data) {
        },
        error: function (data) {
        },
        complete: function (data) {
            loadPins();
            $('#validationErrors').empty();
            $('#title').val(""),
                    $('#author').val(""),
                    tinyMCE.activeEditor.setContent(""),
                    $('#expiration-date').val(""),
                    $('#editPinID').val("0"),
                    $('#editPostDate').val("");
            $('#post-date').val("");
            $.each(data.responseJSON.fieldErrors, function (index,
                    validationError) {
                var errorDiv = $('#validationErrors');
                errorDiv.append(validationError.message).append($('<br>'));
            });
        }
    });
});
//unpublish a pinned post/place into drafts and update any fields that were edited
$('#un-publish-button2').click(function (event) {
    event.preventDefault();
    var expdate = "";
    var postdate = "";
    if ($('#expiration-date').val() == "") {
        expdate = "9999-12-12";
    }
    else {
        expdate = $('#expiration-date').val();
    }

    if ($('#post-date').val() == "") {
        postdate = new Date().toJSON().slice(0, 10).toString();
    }
    else {
        postdate = $('#post-date').val();
    }
    $.ajax({
        type: 'PATCH',
        url: 'pinpost/' + $('#editPinID').val(),
        data: JSON.stringify({
            pinPostID: $('#editPinID').val(),
            title: $('#title').val(),
            author: $('#author').val(),
            content: tinyMCE.activeEditor.getContent({format: 'raw'}),
            postDate: postdate,
            expirationDate: expdate,
            status: 0
        }),
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json; charset=utf-8'
        },
        'dataType': 'json',
        success: function (data) {
        },
        error: function (data) {
        },
        complete: function (data) {
            loadPins();
            $('#title').val(""),
                    $('#author').val(""),
                    tinyMCE.activeEditor.setContent(""),
                    $('#expiration-date').val(""),
                    $('#editPinID').val("0"),
                    $('#editPostDate').val("");
            $('#validationErrors').empty();
            $('#post-date').val("");
            $.each(data.responseJSON.fieldErrors, function (index,
                    validationError) {
                var errorDiv = $('#validationErrors');
                errorDiv.append(validationError.message).append($('<br>'));
            });
        }
    });
});
//displays the blog posts on the home page
function fillDisplaySection(data, status) {
    $('#postRows').empty();
    var cTable = $('#postRows');
    for (i = data.length - 1; i >= 0; i--) {

        var post = data[i];
        if (post.status == 1 && compareDate(new Date(post.postDate))) {
            cTable.append($('<tr>')
                    .append($('<div class="blogpost">')
                            .append($('<h3>').text(post.title))
                            .append($('<div class="authorlabel">').text(post.author))
                            .append($('<p>').text("Post Date: " + post.postDate.substring(0, 10)))
                            .append($(post.content))
                            ));
        }

    }
}

//Displays the pinned posts above the blog posts on the home page
function fillPinDisplaySection(data, status) {
    $('#postPinRows').empty();
    var cTable = $('#postPinRows');
    for (i = data.length - 1; i >= 0; i--) {

        var pin = data[i];
        if (pin.status == 1 && compareDate(new Date(pin.postDate))) {
            cTable.append($('<tr>')
                    .append($('<div class="blogpost">')
                            .append($('<h3>').text(pin.title).append($('<img src="img/pin.png" align="right" height="28" width="28">')))
                            .append($('<p>').text(pin.author))
                            .append($('<p>').text("Post Date: " + pin.postDate.substring(0, 10)))
                            .append($(pin.content))
                            ));
        }
    }
}

//fills active posts table in admin page
function fillActivePosts(data, status) {
    $('#activeRows').empty();
    var cTable = $('#activeRows');
    $.each(data, function (index, post) {

        if (post.status == 1) {
            cTable.append($('<tr>')
                    .append($('<td>').text(post.title))
                    .append($('<td>').text(post.author))
                    .append($('<td>').append($('<a>').attr({'onClick': 'editPost(' + post.postID + ')'}).text('Edit')))
                    .append($('<td>').append($('<a>').attr({'onClick': 'deletePost(' + post.postID + ')'}).text('Delete')))
                    .append($('<td>').text(post.postDate.substring(0, 10)))
                    );
        }
    });
}

//fills the draft blog posts table in the admin page
function fillDraftSection(data, status) {
    $('#draftRows').empty();
    var cTable = $('#draftRows');
    $.each(data, function (index, post) {

        if (post.status == 0) {
            cTable.append($('<tr>')
                    .append($('<td>').text(post.title))
                    .append($('<td>').text(post.author))
                    .append($('<td>')
                            .append($('<a>')
                                    .attr({
                                        'onClick': 'editPost(' +
                                                post.postID + ')'
                                    })
                                    .text('Edit')
                                    ) // ends the <a> tag
                            )
                    .append($('<td>').append($('<a>')
                            .attr({
                                'onClick': 'deletePost(' +
                                        post.postID + ')'
                            })
                            .text('Delete')
                            ) // ends the <a> tag
                            ));
        }
    });
}

//fills the draft section of the pinned post page
function fillPinDraftSection(data, status) {
    $('#draftPinRows').empty();
    var cTable = $('#draftPinRows');
    $.each(data, function (index, pin) {

        if (pin.status == 0) {
            cTable.append($('<tr>')
                    .append($('<td>').text(pin.title))
                    .append($('<td>').text(pin.author))
                    .append($('<td>')
                            .append($('<a>')
                                    .attr({
                                        'onClick': 'editPin(' +
                                                pin.pinPostID + ')'
                                    })
                                    .text('Edit')
                                    ) // ends the <a> tag
                            )
                    .append($('<td>').append($('<a>')
                            .attr({
                                'onClick': 'deletePin(' +
                                        pin.pinPostID + ')'
                            })
                            .text('Delete')
                            ) // ends the <a> tag
                            ));
        }
    });
}

//fills the active pinned posts table in the pinned post page
function fillActivePinPosts(data, status) {
    $('#activePinRows').empty();
    var cTable = $('#activePinRows');
    $.each(data, function (index, pin) {

        if (pin.status == 1) {
            cTable.append($('<tr>')
                    .append($('<td>').text(pin.title))
                    .append($('<td>').text(pin.author))
                    .append($('<td>').append($('<a>').attr({'onClick': 'editPin(' + pin.pinPostID + ')'}).text('Edit')))
                    .append($('<td>').append($('<a>').attr({'onClick': 'deletePin(' + pin.pinPostID + ')'}).text('Delete')))
                    .append($('<td>').text(pin.postDate.substring(0, 10)))
                    );
        }
    });
}

// delete a user
function deleteUser(userID) {
    var answer = confirm("Do you really want to delete this user?");
    if (answer === true) {
        $.ajax({
            type: 'DELETE',
            url: 'user/' + userID
        }).success(function () {
            loadUsers();
        });
    }
}

// delete a blog post
function deletePost(postID) {
    var answer = confirm("Do you really want to delete this post?");
    if (answer === true) {
        $.ajax({
            type: 'DELETE',
            url: 'post/' + postID
        }).success(function () {
            loadPosts();
            displayNumberOfPosts();
        });
    }
}

//delete a pinned post
function deletePin(pinID) {
    var answer = confirm("Do you really want to delete this pin?");
    if (answer === true) {
        $.ajax({
            type: 'DELETE',
            url: 'pinpost/' + pinID
        }).success(function () {
            loadPins();
            displayNumberOfPins();
        });
    }
}

//edit a blog  post
function editPost(postID) {
    event.preventDefault();
    $.ajax({
        type: 'GET',
        url: 'post/' + postID
    }).success(function (post) {
        $.ajax({
            type: 'GET',
            url: 'tags/' + postID
        }).success(function (tags) {
            var content = post.content;
            $('#postID').text(postID);
            $('#title').val(post.title);
            $('#author').val(post.author);
            tinyMCE.activeEditor.setContent(content);
            $('#tags').val(tags);
            $('#post-date').val(post.postDate);
            $('#expiration-date').val(post.expirationDate);
            if (post.expirationDate === "9999-12-12") {
                $('#expiration-date').val("");
            }
            $('#editPostID').val(postID);
            $('#editPostDate').val(post.postDate);
            loadPosts();
        });
    });
}

//displays a the number of active blog posts on the home page
function displayNumberOfPosts() {
    var count = 0;
    $.ajax({
        url: "activeposts"
    }).success(function (data, status) {
        count += data.length;
        $.ajax({
            url: "activepins"
        }).success(function (data2, status) {
            count += data2.length;
            $('#totalPosts').text(count);
        });
    });
}

//displays blog posts with that tag
function getPostsByTag(tag) {
    event.preventDefault();
    $.ajax({
        type: 'GET',
        url: 'tag/' + tag
    }).success(function (data, status) {
        fillDisplaySection(data, status);
    });
}

//edit a pinned post function
function editPin(pinID) {
    event.preventDefault();
    $.ajax({
        type: 'GET',
        url: 'pinpost/' + pinID
    }).success(function (post) {
        var content = post.content;
        $('#pinID').text(pinID);
        $('#title').val(post.title);
        $('#author').val(post.author);
        tinyMCE.activeEditor.setContent(content);
        $('#post-date').val(post.postDate);
        $('#expiration-date').val(post.expirationDate);
        if (post.expirationDate === "9999-12-12") {
            $('#expiration-date').val("");
        }
        $('#editPinID').val(pinID);
        $('#editPostDate').val(post.postDate);
        loadPins();
    });
}

//display active tags on the home screen as buttons that can be used as filters
function displayActiveTags() {
    $.ajax({
        url: "activetags"
    }).success(function (data, status) {
        var tagList = $('#taglist');
        $.each(data, function (index, tag) {
            tagList.append($('<button>').attr({'onClick': 'getPostsByTag( \'' + tag.tag + '\' )'}).text(tag.tag));
        });
    });
}

//compares the post date to the current day to see if it should be active
function compareDate(date1) {
    return new Date(date1).toJSON().slice(0, 10) <= new Date().toJSON().slice(0, 10);
}
