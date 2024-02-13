<template>
  <div class="container">
    <div class="navbar">
      <ul>
        <li><h1>ㅤㅤCatalyst</h1></li>
        <li><a href="#">Home</a></li>
        <li><a href="#">Invite</a></li>
        <li><a href="#">Dashboard</a></li>
        <li class="profile-btn"><a href="#">Profileㅤㅤ</a></li>
      </ul>
    </div>
  </div>
</template>

<style>
* {
  user-select: none;
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
}

body {
  margin: 0;
  padding: 0;
  background-color: #242424;
}

.container {
  display: flex;
  justify-content: center;
}

.navbar {
  box-shadow: 0 0 5px white;
  width: 90%;
  height: 50px;
  border-radius: 8px;
  margin: 10px auto;
  display: flex;
  align-items: center;
}

ul {
  list-style-type: none;
  margin: 0;
  padding: 0;
  overflow: hidden;
  height: 100%;
  display: flex;
  align-items: center;
  width: 100%;
  text-align: center;
}

li {
  margin-right: auto;
}

li a {
  display: block;
  color: white;
  text-align: center;
  padding: 16px;
  text-decoration: none;
  border-radius: 25px;
  margin: 0 10px;
}

li h1 {
  color: white;
  font-size: 1.5rem;
  margin-right: auto;
}

.profile-btn {
  margin-left: auto;
}

.profile-btn a {
  background: none;
}
</style>
