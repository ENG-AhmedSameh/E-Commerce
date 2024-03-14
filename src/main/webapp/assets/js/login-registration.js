function showLogin() {
            document.getElementById('lg1').style.display = 'block';
            document.getElementById('lg2').style.display = 'none';
            document.getElementById('loginTab').classList.add('active');
            document.getElementById('registerTab').classList.remove('active');
}

function showRegister() {
            document.getElementById('lg1').style.display = 'none';
            document.getElementById('lg2').style.display = 'block';
            document.getElementById('loginTab').classList.remove('active');
            document.getElementById('registerTab').classList.add('active');
 }

