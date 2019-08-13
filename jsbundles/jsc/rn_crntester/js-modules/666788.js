__d(function(g,r,i,a,m,e,d){'use strict';var t=(function(t){function o(){var t,n;r(d[1])(this,o);for(var l=arguments.length,s=new Array(l),c=0;c<l;c++)s[c]=arguments[c];return(n=r(d[2])(this,(t=r(d[3])(o)).call.apply(t,[this].concat(s)))).state={theta:new(r(d[4]).Animated.Value)(45)},n._animate=function(){n.state.theta.setValue(0),r(d[4]).Animated.timing(n.state.theta,{toValue:360,duration:5e3}).start(n._animate)},n}return r(d[0])(o,t),r(d[5])(o,[{key:"componentDidMount",value:function(){this._animate()}},{key:"render",value:function(){return r(d[6]).createElement(r(d[4]).View,{style:n.flipCardContainer},r(d[6]).createElement(r(d[4]).Animated.View,{style:[n.flipCard,{transform:[{perspective:850},{rotateX:this.state.theta.interpolate({inputRange:[0,180],outputRange:['0deg','180deg']})}]}]},r(d[6]).createElement(r(d[4]).Text,{style:n.flipText},"This text is flipping great.")),r(d[6]).createElement(r(d[4]).Animated.View,{style:[n.flipCard,n.flipCard1,{transform:[{perspective:850},{rotateX:this.state.theta.interpolate({inputRange:[0,180],outputRange:['180deg','360deg']})}]}]},r(d[6]).createElement(r(d[4]).Text,{style:n.flipText},"On the flip side...")))}}]),o})(r(d[6]).Component),n=r(d[4]).StyleSheet.create({container:{height:500},box1:{left:0,backgroundColor:'green',height:50,position:'absolute',top:0,transform:[{translateX:100},{translateY:50},{rotate:'30deg'},{scaleX:2},{scaleY:2}],width:50},box2:{left:0,backgroundColor:'purple',height:50,position:'absolute',top:0,transform:[{scaleX:2},{scaleY:2},{translateX:100},{translateY:50},{rotate:'30deg'}],width:50},box3step1:{left:0,backgroundColor:'lightpink',height:50,position:'absolute',top:0,transform:[{rotate:'30deg'}],width:50},box3step2:{left:0,backgroundColor:'hotpink',height:50,opacity:.5,position:'absolute',top:0,transform:[{rotate:'30deg'},{scaleX:2},{scaleY:2}],width:50},box3step3:{left:0,backgroundColor:'deeppink',height:50,opacity:.5,position:'absolute',top:0,transform:[{rotate:'30deg'},{scaleX:2},{scaleY:2},{translateX:100},{translateY:50}],width:50},box4:{left:0,backgroundColor:'darkorange',height:50,position:'absolute',top:0,transform:[{translate:[200,350]},{scale:2.5},{rotate:'-0.2rad'}],width:100},box5:{backgroundColor:'maroon',height:50,position:'absolute',right:0,top:0,width:50},box5Transform:{transform:[{translate:[-50,35]},{rotate:'50deg'},{scale:2}]},flipCardContainer:{marginVertical:40,flex:1,alignSelf:'center'},flipCard:{width:200,height:200,alignItems:'center',justifyContent:'center',backgroundColor:'blue',backfaceVisibility:'hidden'},flipCard1:{position:'absolute',top:0,backgroundColor:'red'},flipText:{width:90,fontSize:20,color:'white',fontWeight:'bold'}});e.title='Transforms',e.description='View transforms',e.examples=[{title:'Perspective, Rotate, Animation',description:'perspective: 850, rotateX: Animated.timing(0 -> 360)',render:function(){return r(d[6]).createElement(t,null)}},{title:'Translate, Rotate, Scale',description:"translateX: 100, translateY: 50, rotate: '30deg', scaleX: 2, scaleY: 2",render:function(){return r(d[6]).createElement(r(d[4]).View,{style:n.container},r(d[6]).createElement(r(d[4]).View,{style:n.box1}))}},{title:'Scale, Translate, Rotate, ',description:"scaleX: 2, scaleY: 2, translateX: 100, translateY: 50, rotate: '30deg'",render:function(){return r(d[6]).createElement(r(d[4]).View,{style:n.container},r(d[6]).createElement(r(d[4]).View,{style:n.box2}))}},{title:'Rotate',description:"rotate: '30deg'",render:function(){return r(d[6]).createElement(r(d[4]).View,{style:n.container},r(d[6]).createElement(r(d[4]).View,{style:n.box3step1}))}},{title:'Rotate, Scale',description:"rotate: '30deg', scaleX: 2, scaleY: 2",render:function(){return r(d[6]).createElement(r(d[4]).View,{style:n.container},r(d[6]).createElement(r(d[4]).View,{style:n.box3step2}))}},{title:'Rotate, Scale, Translate ',description:"rotate: '30deg', scaleX: 2, scaleY: 2, translateX: 100, translateY: 50",render:function(){return r(d[6]).createElement(r(d[4]).View,{style:n.container},r(d[6]).createElement(r(d[4]).View,{style:n.box3step3}))}},{title:'Translate, Scale, Rotate',description:"translate: [200, 350], scale: 2.5, rotate: '-0.2rad'",render:function(){return r(d[6]).createElement(r(d[4]).View,{style:n.container},r(d[6]).createElement(r(d[4]).View,{style:n.box4}))}},{title:'Translate, Rotate, Scale',description:"translate: [-50, 35], rotate: '50deg', scale: 2",render:function(){return r(d[6]).createElement(r(d[4]).View,{style:n.container},r(d[6]).createElement(r(d[4]).View,{style:[n.box5,n.box5Transform]}))}}]},666788,[614,616,617,620,516,621,514]);