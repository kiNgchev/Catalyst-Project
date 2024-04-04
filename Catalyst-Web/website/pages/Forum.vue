<template>
  <div class="forum-container-view">
    <div class="forum-portal-view">

    </div>
    <div class="side">
      <div class="side-components">
        <br />
        <h1>Catalyst</h1>
        <h2>Forum</h2>
        <button>Ask question</button>
        <br /><br />
        <button @click="navigateTo('/')">Home</button>
      </div>
    </div>
  </div>
</template>

<style>
.forum-portal-view {

}
.side {
  position: fixed;
  width: 200px;
  min-height: 200vh;
  background-color: black;
}

.side-components button {
  padding: 0 20px;
  font-size: 1rem;
  width: 160px;
  font-weight: 700;
  border-radius: 10px;
  height: 30px;
  border: none;
  cursor: pointer;
}

.side-components {
  justify-content: center;
  align-items: center;
  text-align: center;
  position: relative;
}

.side-components h1 {
  color: white;
  font-weight: 1000;
}

.side-components h2 {
  font-weight: 700;
  color: rgb(166, 166, 166);
}
</style>
