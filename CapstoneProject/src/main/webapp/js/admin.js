$(document).ready(function () {
    loadPosts();
    displayNumberOfPosts();
    displayActiveTags();

    $('#activeAcct').change(function(){
     cb = $(this);
     cb.val(cb.prop('checked'));
 });
  $('#adminAcct').change(function(){
     cb = $(this);
     cb.val(cb.prop('checked'));
 });

    $('#createUser').click(function (event) {
        $.ajax({
            type: 'POST',
            url: 'user',
            data: JSON.stringify({
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
            $('#add-user-name').val(""),
                    $('#add-password').val(""),
                    $('#activeAcct').val(""),
                    $('#adminAcct').val(""),
                    loadPosts();
//                $('#validationErrors').empty();
        }).error(function (data, status) {
            console.log("error");
//                $('#validationErrors').empty();
//                $.each(data.responseJSON.fieldErrors, function (index,
//                        validationError) {
//                    var errorDiv = $('#validationErrors');
//                    errorDiv.append(validationError.message).append($('<br>'));
//                });
        });
    });

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

function loadPosts() {


    loadPins()

    $.ajax({
        url: "posts"
    }).success(function (data, status) {
        fillDraftSection(data, status);
        fillActivePosts(data, status);
        fillDisplaySection(data, status);
    });
}

function loadPins() {
    $.ajax({
        url: "pinposts"
    }).success(function (data, status) {
        fillPinDraftSection(data, status);
        fillActivePinPosts(data, status);
        fillPinDisplaySection(data, status);
    });
}


$('.showall-button').click(function (event) {
    loadPosts();
});

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



















































































































































































$(document).ready(function () {
    loadPins();
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

function fillDisplaySection(data, status) {
    $('#postRows').empty();
    var cTable = $('#postRows');

    for (i = data.length - 1; i >= 0; i--) {

        var post = data[i];
        console.log(post.postDate);
        if (post.status == 1 && compareDate(new Date(post.postDate))) {
            cTable.append($('<tr>')
                    .append($('<div class="blogpost">')
                            .append($('<h3>').text(post.title))
//                    .append($('<img class="profilepic">'))
                            .append($('<div class="authorlabel">').text(post.author))
                            .append($('<p>').text("Post Date: " + post.postDate.substring(0, 10)))
                            .append($(post.content))
                            ));
        }

    }
}

function fillPinDisplaySection(data, status) {
    $('#postPinRows').empty();
    var cTable = $('#postPinRows');

    for (i = data.length - 1; i >= 0; i--) {

        var pin = data[i];
        console.log(pin.postDate);
        if (pin.status == 1 && compareDate(new Date(pin.postDate))) {
            cTable.append($('<tr>')
                    .append($('<div class="blogpost">')
                            .append($('<h3>').text(pin.title).append($('<img src="img/pin.png" align="right" height="28" width="28">')))

//                    .append($('<a class="col-lg-offset-3 col-lg-2">').text("pinned - "))
//                    .append($('</div>'))
//                    .append($('<div class="row">'))
                            .append($('<p>').text(pin.author))
                            .append($('<p>').text("Post Date: " + pin.postDate.substring(0, 10)))
                            .append($(pin.content))
                            ));
        }

    }
}

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




function deletePost(postID) {
    var answer = confirm("Do you really want to delete this post?");
    if (answer === true) {
        $.ajax({
            type: 'DELETE',
            url: 'post/' + postID
        }).success(function () {
            loadPosts();
            displayNumberOfPosts();
            // displayActiveTags();
        });
    }
}
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

//$('#detailsModal').on('show.bs.modal', function (event) {
//    var element = $(event.relatedTarget);
//    var postId = element.data('post-id');
//
//    var modal = $(this);
//    $.ajax({
//        type: 'GET',
//        url: 'post/' + postId
//    }).success(function (post) {
//        modal.find('#post-id').text(post.postID);
//        modal.find('#post-firstName').text(post.title);
//        modal.find('#post-lastName').text(post.author);
//        modal.find('#post-company').text(post.content);
//        modal.find('#post-phone').text(post.postDate);
//        modal.find('#post-email').text(post.expirationDate);
//    });
//});




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

function getPostsByTag(tag) {

    event.preventDefault();
    $.ajax({
        type: 'GET',
        url: 'tag/' + tag
    }).success(function (data, status) {
        fillDisplaySection(data, status);
    });
}
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

function getPostsByTag(tag) {

    event.preventDefault();
    $.ajax({
        type: 'GET',
        url: 'tag/' + tag
    }).success(function (data, status) {
        fillDisplaySection(data, status);
    });


}

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

function compareDate(date1) {
    console.log(new Date(date1).toJSON().slice(0, 10));
    console.log(new Date().toJSON().slice(0, 10));
    return new Date(date1).toJSON().slice(0, 10) <= new Date().toJSON().slice(0, 10);

}


