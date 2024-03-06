<template>
    <div class="container">
    <tr>
      <td><img class="img-plugins-row-view" :src="item.image" alt="Plugin image"></td>
      <td><h1>{{ item.title }}</h1></td>
      <br>
      <td><h2>{{ item.description }}</h2></td>
      <td><button @click="handleButtonClick">Add</button></td>
    </tr>
</div>
  </template>
  
  <script>
  export default {
    props: {
      item: Object
    },
    methods: {
      handleButtonClick() {
        
        }
    }
}
</script>
  
<style>
td button {
    border: none;
    width: 100px;
    cursor: pointer;
    border-radius: 10px;
    height: 50px;
    font-size: 1rem;
    color: #765D69;
    background-color: #FCD0BA;
    transition-duration: 1s;
}

td button:hover {
    transform: scale(1.1);
    color: #FCD0BA;
    background-color: #765D69;
}

td h1, td h2 {
    color: white;
}
.container {
    justify-content: center;
    align-items: center;
    text-align: center;
  }

.img-plugins-row-view {
    height: 200px;
    width: 200px;
    border-radius: 10px;
}
</style>
