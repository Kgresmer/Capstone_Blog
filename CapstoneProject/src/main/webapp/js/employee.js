$(document).ready(function () {
    loadPosts();
    $('#add-button').click(function (event) {
        event.preventDefault();
        
        
        $.ajax({
            type: 'POST',
            url: 'post',
            data: JSON.stringify({
                title: $('#title').val(),
                author: $('#author').val(),
                content: tinyMCE.activeEditor.getContent({format: 'raw'}),
                tags: $('#tags').val(),
                expirationDate: $('#expiration-date').val()
            }),
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json; charset=utf-8'
            },
            'dataType': 'json'
        }).success(function (data, status) {
            $('#title').val(""),
                    $('#author').val(""),
                    $('#mytextarea').val(""),
                    $('#tags').val(""),
                    $('#expiration-date').val("");
            loadPosts();
            $('#validationErrors').empty();
        }).error(function (data, status) {
            alert('error');
//            if (status === 'parsererror') {
//                $('#title').val('');
//                alert('You cannot have two posts with the same title.');
//            } else {
//                $('#validationErrors').empty();
//                $.each(data.responseJSON.fieldErrors, function (index, validationError) {
//                    var errorDiv = $('#validationErrors');
//                    errorDiv.append(validationError.message).append($('<br>'));
//                });
//            }
        });

    });

});

function loadPosts() {
    $.ajax({
        url: "posts"
    }).success(function (data, status) {
        fillDraftSection(data, status);
        fillDisplaySection(data, status);
    });
}


function fillDisplaySection(data, status) {
    $('#postRows').empty();
    var cTable = $('#postRows');

    $.each(data, function (index, post) {
        cTable.append($('<tr>')
                .append($('<h3>').text(post.title))
                .append($('<p>').text(post.author))
                .append($('<p>').text("Post Date: " + post.postDate))
                .append($(post.content))
                .append($('<img id="blogpic" src="http://oakmeyergardens.org/wp-content/uploads/2012/03/small_tree.jpeg"/>'))
                .append($('<hr>'))
                );
    });
}

function fillActivePosts(data, status) {
    $('#activeRows').empty();
    var cTable = $('#activeRows');

    $.each(data, function (index, post) {
        cTable.append($('<tr>')
                .append($('<td>')
                        .append($('<a>')
                                .attr({
                                    'data-post-id': post.postId,
                                    'data-toggle': 'modal',
                                    'data-target': '#detailsModal'
                                })
                                .text(post.title)
                                ) // ends the <a> tag
                        )
                .append($('<td>').text(post.author))

                );
    });
}

//function fillDraftSection(data, status) {
//    $('#draftRows').empty();
//    var cTable = $('#draftRows');
//
//    $.each(data, function (index, post) {
//        cTable.append($('<tr>')
//                .append($('<td>').text(post.title))
//                .append($('<td>').text(post.author))
//                .append($('<td>')
//                        .append($('<a>')
//                                .attr({
//                                    'onClick': 'editPost(' +
//                                    post.postID + ')'
//                                })
//                                .text('Edit')
//                                ) // ends the <a> tag
//                        )
//                .append($('<td>').append($('<a>')
//                        .attr({
//                            'onClick': 'deletePost(' +
//                                    post.postID + ')'
//                        })
//                        .text('Delete')
//                        ) // ends the <a> tag
//                        ));
//    });
//}




//function deletePost(postID) {
//    var answer = confirm("Do you really want to delete this post?");
//    if (answer === true) {
//        $.ajax({
//            type: 'DELETE',
//            url: 'post/' + postID
//        }).success(function () {
//            loadPosts();
//        });
//    }
//}

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
//        data: JSON.stringify({
//            title: $('#title').val(),
//            author: $('#author').val(),
//            content: tinyMCE.activeEditor.getContent({format: 'raw'}),  
//            tags: $('#tags').val(),
//            postDate: "2015-05-08",
//            expirationDate: $('#expiration-date').val()
//        }),
//        headers: {
//            'Accept': 'application/json',
//            'Content-Type': 'application/json'
//        },
//        'dataType': 'json'
    }).success(function (post) {
        var content = post.content;
         $('#title').val(post.title),
                $('#author').val(post.author),
                tinyMCE.activeEditor.setContent(content),
                $('#tags').val(post.tags),
                $('#expiration-date').val(post.expirationDate);
        loadPosts();
    });
};




