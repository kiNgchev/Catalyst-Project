<template>
  <div
    class="navbar-container"
    ref="navbarContainer"
    @mouseleave="handleMouseLeave"
  >
    <div class="navbar-flight">
      <ul>
        <li><h1 style="color: #b0adff">ㅤㅤCatalyst</h1></li>
        <li><button @click="navigateTo('/')">Home</button></li>
        <li>
          <button
            onclick="window.location.href='https://discord.com/api/oauth2/authorize?client_id=1207361111062609951&permissions=0&scope=bot'"
          >
            Invite
          </button>
        </li>
        <li><button @click="navigateTo('/Dashboard')">Dashboard</button></li>
        <li><button @click="navigateTo('/Forum')">Forum</button></li>
        <li><button @click="navigateTo('/Plugins')">Plugins</button></li>
        <li class="profile-btn">
          <button @click="navigateTo('/Profile')">Profile</button>
        </li>
      </ul>
    </div>
  </div>
</template>

<style>
* {
  user-select: none;
  font-family: "Segoe UI", Tahoma, Geneva, Verdana, sans-serif;
}

body {
  margin: 0;
  padding: 0;
  overflow: hidden;
  background-color: #141417;
}

.navbar-container {
  display: flex;
  justify-content: center;
  transition: transform 1s ease;
}

.navbar-container:hover {
  transform: scale(1.04);
  transition: transform 0.5s ease;
}
.developers-picture {
  width: 100px;
  border-radius: 20px;
  height: 100px;
}

.developers-container h3 {
  font-weight: 1000;
  color: #b0adff;
}

.content-container {
  justify-content: center;
  align-items: center;
  color: white;
  text-align: center;
}

.navbar-flight {
  border: 1.5px solid white;
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

li button {
  display: block;
  color: white;
  text-align: center;
  background: none;
  border: none;
  padding: 16px;
  text-decoration: none;
  border-radius: 25px;
  margin: 0 10px;
  font-size: 1.2rem;
  cursor: pointer;
  font-weight: 600;
}
li button:hover {
  text-decoration: underline;
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

<script>
export default {
  methods: {
    handleMouseLeave(event) {
      if (event.clientY < 50 && event.clientX < 50) {
        this.$refs.navbarContainer.style.transitionDuration = "1s";
      } else {
        this.$refs.navbarContainer.style.transitionDuration = "2s";
      }
    },
  },
};
</script>
