<template>
  <q-page class="flex flex-center">
    <q-form class="q-gutter-md">
      <q-input label="Your email *" v-model="email" hint="Email"/>

      <q-input type="password" label="Your password *" v-model="pass" hint="Password"/>
      <div>
        <q-btn label="Submit" @click="loginLocal" color="primary"/>
        <q-btn  label="Sign in with Google" @click="loginGoogle" color="blue"/>
        
      </div>
    </q-form>
  </q-page>
</template>

<script>
export default {
  name: 'Login',
  data() {
    return {
      email:'',
      pass:'',
      linkRedirectGoogle: 'http://localhost:3000/login/google',
      linkRedirectLocal: 'http://localhost:3000/login/local',
    }
  },
  methods:{
    async loginLocal(){
      console.log('llega')
      let loger = await this.$axios.post(this.linkRedirectLocal,{
        email:this.email,
        password:this.pass
      },{
        headers:{
          'Content-Type': 'application/json'
      }
      })
      localStorage.setItem('token',await loger.data)
      this.$router.push('posts')
    },
    loginGoogle(){

      window.location.href = this.linkRedirectGoogle;
    }

  }
  
}
</script>
