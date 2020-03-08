<template>
  <q-page class="flex flex-center">
    <img
      alt="Quasar logo"
      src="~assets/quasar-logo-full.svg"
    >
    <postList v-for="post in posts" :key="post.id" v-bind="post" >

    </postList>
  </q-page>
</template>

<script>
import PostList from '../components/PostList'
export default {
  name: 'Posts',
  components:{
      PostList
  },
  data: {
      posts: [],
      postsFilter: []
  },
  created: async function(){
      await this.getAll()
  },
  methods:{
      async getAll(){
          let promiseJson = await fetch('https://www.googleapis.com/blogger/v3/blogs/6332784932555712614/posts?access_token=' + localStorage.getItem('accesToken'), {
                method: 'GET',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
            })

            let responseJson = await promiseJson.json();
            
            let posts = await responseJson.items;
            posts.forEach(post => {
                let postOobject = {
                    idPost: post.id,
                    title: post.title,
                    content: post.content,
                    author: post.author.displayName,
                    date: post.published
                };

                this.posts.push(postOobject);

            })

       }
  }

}
</script>
