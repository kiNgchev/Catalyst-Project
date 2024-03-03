<template>
  <div class="" v-if="loaded">
    <Navbar/>
    <br/>
    <h3 class="view-our-commands">VIEW OUR COMMANDS</h3>
      <div class="commands-pre-container">
            <marquee behavior="" direction="" scrollamount="20">
              <div class="pre-command-container">
                ㅤㅤㅤ<h1>/kick</h1>
                ㅤㅤㅤ<h1>/ban</h1>
                ㅤㅤㅤ<h1>/userinfo</h1>
                ㅤㅤㅤ<h1>/mute</h1>
                ㅤㅤㅤ<h1>/lock</h1>
                ㅤㅤㅤ<h1>/purge</h1>
                ㅤㅤㅤ<h1>/report</h1>
                ㅤㅤㅤ<h1>/warn</h1>
                ㅤㅤㅤ<h1>/warns</h1>
                ㅤㅤㅤ<h1>/avatar</h1>
                ㅤㅤㅤ<h1>/guildinfo</h1>
                ㅤㅤㅤ<h1>/channelinfo</h1>
                ㅤㅤㅤ<h1>/emojis</h1>
                ㅤㅤㅤ<h1>/roles</h1>
                ㅤㅤㅤ<h1>/ping</h1>
                ㅤㅤㅤ<h1>/config</h1>
                ㅤㅤㅤ<h1>/permissions</h1>
                ㅤㅤㅤ<h1>/list</h1>
                ㅤㅤㅤ<h1>/dashboard</h1>
                ㅤㅤㅤ<h1>/donate</h1>
                ㅤㅤㅤ<h1>/balance</h1>
                ㅤㅤㅤ<h1>Coming Soon...</h1>
              </div>
            </marquee>
      </div>
      <br/><br><br>
    <div class="content-container">
      <div class="developers-container">
        <h3>TEAM</h3>
        <h1>Developers of project</h1>
        <br><img src="/avatars/OpenStationDev.png" alt="OpenStation" class="developers-picture">
        <h2>OpenStation(KYBEX11) - Owner, Frontend, Backend Developer</h2>
        <img src="/avatars/kiNgchev.gif" alt="kiNgchev" class="developers-picture">
        <h2>kiNgchev - Owner, Backend Developer</h2>
      </div>
    </div>
  </div>
  <div class="" v-else>
    <div class="de-container">
      <div class="typewriter">
        <div class="slide"><i></i></div>
        <div class="paper"></div>
        <div class="keyboard"></div>
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

.view-our-commands {
  text-align: center;
}

.commands-pre-container {
  background: #17171A;
  color: white;
}

.pre-command-container {
  display: flex;
}

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

.developers-container h3,
.view-our-commands {
  font-weight: 800;
  color: #B0ADFF;
}

.typewriter {
  --blue: #5C86FF;
  --blue-dark: #275EFE;
  --key: #fff;
  --paper: #EEF0FD;
  --text: #D3D4EC;
  --tool: #FBC56C;
  --duration: 3s;
  position: relative;
  -webkit-animation: bounce05 var(--duration) linear infinite;
  animation: bounce05 var(--duration) linear infinite;
}

.typewriter .slide {
  width: 92px;
  height: 20px;
  border-radius: 3px;
  margin-left: 14px;
  transform: translateX(14px);
  background: linear-gradient(var(--blue), var(--blue-dark));
  -webkit-animation: slide05 var(--duration) ease infinite;
  animation: slide05 var(--duration) ease infinite;
}

.typewriter .slide:before, .typewriter .slide:after,
.typewriter .slide i:before {
  content: "";
  position: absolute;
  background: var(--tool);
}

.typewriter .slide:before {
  width: 2px;
  height: 8px;
  top: 6px;
  left: 100%;
}

.typewriter .slide:after {
  left: 94px;
  top: 3px;
  height: 14px;
  width: 6px;
  border-radius: 3px;
}

.typewriter .slide i {
  display: block;
  position: absolute;
  right: 100%;
  width: 6px;
  height: 4px;
  top: 4px;
  background: var(--tool);
}

.typewriter .slide i:before {
  right: 100%;
  top: -2px;
  width: 4px;
  border-radius: 2px;
  height: 14px;
}

.typewriter .paper {
  position: absolute;
  left: 24px;
  top: -26px;
  width: 40px;
  height: 46px;
  border-radius: 5px;
  background: var(--paper);
  transform: translateY(46px);
  -webkit-animation: paper05 var(--duration) linear infinite;
  animation: paper05 var(--duration) linear infinite;
}

.typewriter .paper:before {
  content: "";
  position: absolute;
  left: 6px;
  right: 6px;
  top: 7px;
  border-radius: 2px;
  height: 4px;
  transform: scaleY(0.8);
  background: var(--text);
  box-shadow: 0 12px 0 var(--text), 0 24px 0 var(--text), 0 36px 0 var(--text);
}

.typewriter .keyboard {
  width: 120px;
  height: 56px;
  margin-top: -10px;
  z-index: 1;
  position: relative;
}

.typewriter .keyboard:before, .typewriter .keyboard:after {
  content: "";
  position: absolute;
}

.typewriter .keyboard:before {
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  border-radius: 7px;
  background: linear-gradient(135deg, var(--blue), var(--blue-dark));
  transform: perspective(10px) rotateX(2deg);
  transform-origin: 50% 100%;
}

.typewriter .keyboard:after {
  left: 2px;
  top: 25px;
  width: 11px;
  height: 4px;
  border-radius: 2px;
  box-shadow: 15px 0 0 var(--key), 30px 0 0 var(--key), 45px 0 0 var(--key), 60px 0 0 var(--key), 75px 0 0 var(--key), 90px 0 0 var(--key), 22px 10px 0 var(--key), 37px 10px 0 var(--key), 52px 10px 0 var(--key), 60px 10px 0 var(--key), 68px 10px 0 var(--key), 83px 10px 0 var(--key);
  -webkit-animation: keyboard05 var(--duration) linear infinite;
  animation: keyboard05 var(--duration) linear infinite;
}

@keyframes bounce05 {
  85%, 92%, 100% {
    transform: translateY(0);
  }

  89% {
    transform: translateY(-4px);
  }

  95% {
    transform: translateY(2px);
  }
}

@keyframes slide05 {
  5% {
    transform: translateX(14px);
  }

  15%, 30% {
    transform: translateX(6px);
  }

  40%, 55% {
    transform: translateX(0);
  }

  65%, 70% {
    transform: translateX(-4px);
  }

  80%, 89% {
    transform: translateX(-12px);
  }

  100% {
    transform: translateX(14px);
  }
}

@keyframes paper05 {
  5% {
    transform: translateY(46px);
  }

  20%, 30% {
    transform: translateY(34px);
  }

  40%, 55% {
    transform: translateY(22px);
  }

  65%, 70% {
    transform: translateY(10px);
  }

  80%, 85% {
    transform: translateY(0);
  }

  92%, 100% {
    transform: translateY(46px);
  }
}

@keyframes keyboard05 {
  5%, 12%, 21%, 30%, 39%, 48%, 57%, 66%, 75%, 84% {
    box-shadow: 15px 0 0 var(--key), 30px 0 0 var(--key), 45px 0 0 var(--key), 60px 0 0 var(--key), 75px 0 0 var(--key), 90px 0 0 var(--key), 22px 10px 0 var(--key), 37px 10px 0 var(--key), 52px 10px 0 var(--key), 60px 10px 0 var(--key), 68px 10px 0 var(--key), 83px 10px 0 var(--key);
  }

  9% {
    box-shadow: 15px 2px 0 var(--key), 30px 0 0 var(--key), 45px 0 0 var(--key), 60px 0 0 var(--key), 75px 0 0 var(--key), 90px 0 0 var(--key), 22px 10px 0 var(--key), 37px 10px 0 var(--key), 52px 10px 0 var(--key), 60px 10px 0 var(--key), 68px 10px 0 var(--key), 83px 10px 0 var(--key);
  }

  18% {
    box-shadow: 15px 0 0 var(--key), 30px 0 0 var(--key), 45px 0 0 var(--key), 60px 2px 0 var(--key), 75px 0 0 var(--key), 90px 0 0 var(--key), 22px 10px 0 var(--key), 37px 10px 0 var(--key), 52px 10px 0 var(--key), 60px 10px 0 var(--key), 68px 10px 0 var(--key), 83px 10px 0 var(--key);
  }

  27% {
    box-shadow: 15px 0 0 var(--key), 30px 0 0 var(--key), 45px 0 0 var(--key), 60px 0 0 var(--key), 75px 0 0 var(--key), 90px 0 0 var(--key), 22px 12px 0 var(--key), 37px 10px 0 var(--key), 52px 10px 0 var(--key), 60px 10px 0 var(--key), 68px 10px 0 var(--key), 83px 10px 0 var(--key);
  }

  36% {
    box-shadow: 15px 0 0 var(--key), 30px 0 0 var(--key), 45px 0 0 var(--key), 60px 0 0 var(--key), 75px 0 0 var(--key), 90px 0 0 var(--key), 22px 10px 0 var(--key), 37px 10px 0 var(--key), 52px 12px 0 var(--key), 60px 12px 0 var(--key), 68px 12px 0 var(--key), 83px 10px 0 var(--key);
  }

  45% {
    box-shadow: 15px 0 0 var(--key), 30px 0 0 var(--key), 45px 0 0 var(--key), 60px 0 0 var(--key), 75px 0 0 var(--key), 90px 2px 0 var(--key), 22px 10px 0 var(--key), 37px 10px 0 var(--key), 52px 10px 0 var(--key), 60px 10px 0 var(--key), 68px 10px 0 var(--key), 83px 10px 0 var(--key);
  }

  54% {
    box-shadow: 15px 0 0 var(--key), 30px 2px 0 var(--key), 45px 0 0 var(--key), 60px 0 0 var(--key), 75px 0 0 var(--key), 90px 0 0 var(--key), 22px 10px 0 var(--key), 37px 10px 0 var(--key), 52px 10px 0 var(--key), 60px 10px 0 var(--key), 68px 10px 0 var(--key), 83px 10px 0 var(--key);
  }

  63% {
    box-shadow: 15px 0 0 var(--key), 30px 0 0 var(--key), 45px 0 0 var(--key), 60px 0 0 var(--key), 75px 0 0 var(--key), 90px 0 0 var(--key), 22px 10px 0 var(--key), 37px 10px 0 var(--key), 52px 10px 0 var(--key), 60px 10px 0 var(--key), 68px 10px 0 var(--key), 83px 12px 0 var(--key);
  }

  72% {
    box-shadow: 15px 0 0 var(--key), 30px 0 0 var(--key), 45px 2px 0 var(--key), 60px 0 0 var(--key), 75px 0 0 var(--key), 90px 0 0 var(--key), 22px 10px 0 var(--key), 37px 10px 0 var(--key), 52px 10px 0 var(--key), 60px 10px 0 var(--key), 68px 10px 0 var(--key), 83px 10px 0 var(--key);
  }

  81% {
    box-shadow: 15px 0 0 var(--key), 30px 0 0 var(--key), 45px 0 0 var(--key), 60px 0 0 var(--key), 75px 0 0 var(--key), 90px 0 0 var(--key), 22px 10px 0 var(--key), 37px 12px 0 var(--key), 52px 10px 0 var(--key), 60px 10px 0 var(--key), 68px 10px 0 var(--key), 83px 10px 0 var(--key);
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
