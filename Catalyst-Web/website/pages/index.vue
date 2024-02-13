<template>
<div class="">
  <br>
  <div class="navbar">
    <ul>
      <li><a href="#">Home</a></li>
      <li><a href="#">Invite</a></li>
      <li><a href="#">Dashboard</a></li>
      <li class="profile-btn"><a href="#">Profile</a></li>
    </ul>
  </div>
</div>
</template>

<style>
* {
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
}

body {
  margin: 0;
  padding: 0;
  background-color: #242424;
}

.navbar {
  box-shadow: 0 0 5px  white;
  width: 90%;
  height: 50px;
  border-radius: 25px;
  margin: 10px auto;
}

ul {
  list-style-type: none;
  margin: 0;
  padding: 0;
  overflow: hidden;
  height: 100%;
}

li {
  float: left;
}

li a {
  display: block;
  color: white;
  text-align: center;
  padding: 16px;
  text-decoration: none;
  border-radius: 25px;

}

/*li a:hover {
  background-color: #000;
  color: white;
}*/

.profile-btn {
  float: right;
}

.profile-btn a {
  background: none;
}
</style>
