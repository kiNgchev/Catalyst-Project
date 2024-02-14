<template>
  <div class="container">
    <div class="navbar">
      <ul>
        <li><h1>ㅤㅤCatalyst</h1></li>
        <li><a>Home</a></li>
        <li><a>Invite</a></li>
        <li><a>Dashboard</a></li>
        <li class="profile-btn"><a>Profileㅤㅤ</a></li>
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
  background-color: #141417;
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
  font-size: 1.2rem;
  font-weight: 600;
}

li h1 {
  color: white;
  font-size: 1.5rem;
  margin-right: auto;
  font-weight: 800;
}

.profile-btn {
  margin-left: auto;
}

.profile-btn a {
  background: none;
}
</style>
