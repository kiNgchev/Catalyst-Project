<template>
  <div class="container">
  <Navbar/>
  <h1>Explore a plugins</h1>
  <Table :items="items"/>
  </div>
</template>

<script>
export default {
  data() {
    return {
      items: [
        {
          image: '/plugins//empty-view.png',
          title: 'Plugin 1',
          description: 'Price - free'
        },
        {
          image: '/plugins/empty-view.png',
          title: 'Plugin 2',
          description: 'Price - free'
        },
        {
          image: '/plugins/empty-view.png',
          title: 'Plugin 3',
          description: 'Price - 1$'
        }
      ]
    }
  }
}
</script>

<style>
h1 {
  color: white;
}

.container {
  justify-content: center;
  align-items: center;
  text-align: center;
}
</style>
