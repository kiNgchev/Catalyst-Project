<template>
  <div class="" v-if="loaded">
    <Navbar/>
    <div class="content-container">
      <div class="developers-container">
        <h3>TEAM</h3>
        <h1>Developers of project</h1>
        <br><img src="/avatars/OpenStationDev.png" alt="OpenStation" class="developers-picture">
        <h2>OpenStation(KYBEX11) - Frontend Developer</h2>
        <img src="/avatars/kiNgchev.gif" alt="kiNgchev" class="developers-picture">
        <h2>kiNgchev - Founder, Backend Developer</h2>
      </div>
    </div>
  </div>
  <div class="" v-else>
    <div class="de-container">
      <div class="loader">
        <div class="box1"></div>
        <div class="box2"></div>
        <div class="box3"></div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      loaded: false,
    }
  },
  mounted() {
    setTimeout(() => {
      this.loaded = true;
    }, 5000);
  }
}
</script>

<style>
.de-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
}

* {
  user-select: none;
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
}

body {
  margin: 0;
  padding: 0;
  background-color: #141417;
}

.navbar-container {
  display: flex;
  justify-content: center;
}

.developers-picture {
  width: 100px;
  border-radius: 20px;
  height: 100px;
}

.developers-container h3 {
  font-weight: 1000;
  color: #B0ADFF;
}

.loader {
  width: 112px;
  height: 112px;
}

.box1,
.box2,
.box3 {
  border: 16px solid #f5f5f5;
  box-sizing: border-box;
  position: absolute;
  display: block;
}

.box1 {
  width: 112px;
  height: 48px;
  margin-top: 64px;
  margin-left: 0px;
  animation: abox1 4s 1s forwards ease-in-out infinite;
}

.box2 {
  width: 48px;
  height: 48px;
  margin-top: 0px;
  margin-left: 0px;
  animation: abox2 4s 1s forwards ease-in-out infinite;
}

.box3 {
  width: 48px;
  height: 48px;
  margin-top: 0px;
  margin-left: 64px;
  animation: abox3 4s 1s forwards ease-in-out infinite;
}

@keyframes abox1 {
  0% {
    width: 112px;
    height: 48px;
    margin-top: 64px;
    margin-left: 0px;
  }

  12.5% {
    width: 48px;
    height: 48px;
    margin-top: 64px;
    margin-left: 0px;
  }

  25% {
    width: 48px;
    height: 48px;
    margin-top: 64px;
    margin-left: 0px;
  }

  37.5% {
    width: 48px;
    height: 48px;
    margin-top: 64px;
    margin-left: 0px;
  }

  50% {
    width: 48px;
    height: 48px;
    margin-top: 64px;
    margin-left: 0px;
  }

  62.5% {
    width: 48px;
    height: 48px;
    margin-top: 64px;
    margin-left: 0px;
  }

  75% {
    width: 48px;
    height: 112px;
    margin-top: 0px;
    margin-left: 0px;
  }

  87.5% {
    width: 48px;
    height: 48px;
    margin-top: 0px;
    margin-left: 0px;
  }

  100% {
    width: 48px;
    height: 48px;
    margin-top: 0px;
    margin-left: 0px;
  }
}

@keyframes abox2 {
  0% {
    width: 48px;
    height: 48px;
    margin-top: 0px;
    margin-left: 0px;
  }

  12.5% {
    width: 48px;
    height: 48px;
    margin-top: 0px;
    margin-left: 0px;
  }

  25% {
    width: 48px;
    height: 48px;
    margin-top: 0px;
    margin-left: 0px;
  }

  37.5% {
    width: 48px;
    height: 48px;
    margin-top: 0px;
    margin-left: 0px;
  }

  50% {
    width: 112px;
    height: 48px;
    margin-top: 0px;
    margin-left: 0px;
  }

  62.5% {
    width: 48px;
    height: 48px;
    margin-top: 0px;
    margin-left: 64px;
  }

  75% {
    width: 48px;
    height: 48px;
    margin-top: 0px;
    margin-left: 64px;
  }

  87.5% {
    width: 48px;
    height: 48px;
    margin-top: 0px;
    margin-left: 64px;
  }

  100% {
    width: 48px;
    height: 48px;
    margin-top: 0px;
    margin-left: 64px;
  }
}

@keyframes abox3 {
  0% {
    width: 48px;
    height: 48px;
    margin-top: 0px;
    margin-left: 64px;
  }

  12.5% {
    width: 48px;
    height: 48px;
    margin-top: 0px;
    margin-left: 64px;
  }

  25% {
    width: 48px;
    height: 112px;
    margin-top: 0px;
    margin-left: 64px;
  }

  37.5% {
    width: 48px;
    height: 48px;
    margin-top: 64px;
    margin-left: 64px;
  }

  50% {
    width: 48px;
    height: 48px;
    margin-top: 64px;
    margin-left: 64px;
  }

  62.5% {
    width: 48px;
    height: 48px;
    margin-top: 64px;
    margin-left: 64px;
  }

  75% {
    width: 48px;
    height: 48px;
    margin-top: 64px;
    margin-left: 64px;
  }

  87.5% {
    width: 48px;
    height: 48px;
    margin-top: 64px;
    margin-left: 64px;
  }

  100% {
    width: 112px;
    height: 48px;
    margin-top: 64px;
    margin-left: 0px;
  }
}


.content-container {
  justify-content: center;
  align-items: center;
  color: white;
  text-align: center;
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
