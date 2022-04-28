onload = function(ev) {
    // var loginForm = document.getElementById('login_form');
    // var userId = document.getElementById('user_id');
    // var userPw = document.getElementById('user_pw');
    // loginForm.onsubmit = function(ev){ 
    //     if(userId.value=='') {
    //         alert('아이디를 입력하세요.');
    //         ev.preventDefault(); 
    //     }
    //     if(userPw.value=='') {
    //         alert('비밀번호를 입력하세요.');
    //         ev.preventDefault(); 
    //     }            
    // };
    $('#login_form').on('submit', function(ev){
        if($('#user_id').val() == ''){
            alert('아이디를 입력하세요.');
            ev.preventDefault(); 
        }
        if($('#user_pw').val() == ''){
            alert('비밀번호를 입력하세요.');
            ev.preventDefault(); 
        }
    });

    var userIdWrong = $('#user_id_wrong');
    var userIdCorrect = $('#user_id_correct');
    $('#user_id').on('keyup', function(ev){
        if($('#user_id').val().length < 4) {
            userIdWrong.show();
            userIdCorrect.hide();
        } else{
            userIdWrong.hide();
            userIdCorrect.show();
        }
    });

}

