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
          image: '/avatars/kiNgchev.gif',
          title: 'kiNgchev',
          description: 'Price - 0$'
        },
        {
          image: '/avatars/OpenStationDev.png',
          title: 'OpenStationDev',
          description: 'Price - 0$'
        },
        {
          image: '/avatars/mefaldemisov.jpg',
          title: 'MefAldemisov',
          description: 'Price - 0$'
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
