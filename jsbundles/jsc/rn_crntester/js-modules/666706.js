__d(function(g,r,i,a,m,e,d){'use strict';var t=(function(t){function n(){return r(d[1])(this,n),r(d[2])(this,r(d[3])(n).apply(this,arguments))}return r(d[0])(n,t),r(d[4])(n,[{key:"render",value:function(){return r(d[5]).createElement(r(d[6]).View,{style:o.textBubbleBackground},r(d[5]).createElement(r(d[6]).Text,{style:o.text},"Text Message"))}}]),n})(r(d[5]).PureComponent),n=(function(t){function n(){var t,l;r(d[1])(this,n);for(var o=arguments.length,u=new Array(o),c=0;c<o;c++)u[c]=arguments[c];return(l=r(d[2])(this,(t=r(d[3])(n)).call.apply(t,[this].concat(u)))).state={text:''},l}return r(d[0])(n,t),r(d[4])(n,[{key:"render",value:function(){var t=this;return r(d[5]).createElement(r(d[6]).View,{style:o.textInputContainer},r(d[5]).createElement(r(d[6]).TextInput,{style:o.textInput,onChangeText:function(n){t.setState({text:n})},value:this.state.text,placeholder:'Type a message...'}),r(d[5]).createElement(r(d[6]).Button,{onPress:function(){r(d[6]).Alert.alert('You tapped the button!')},title:"Send"}))}}]),n})(r(d[5]).PureComponent),l=(function(l){function u(){return r(d[1])(this,u),r(d[2])(this,r(d[3])(u).apply(this,arguments))}return r(d[0])(u,l),r(d[4])(u,[{key:"render",value:function(){return r(d[5]).createElement(r(d[5]).Fragment,null,r(d[5]).createElement(r(d[6]).ScrollView,{style:o.fill,keyboardDismissMode:"interactive"},Array(15).fill().map(function(n,l){return r(d[5]).createElement(t,{key:l})})),r(d[5]).createElement(r(d[6]).InputAccessoryView,{backgroundColor:"#fffffff7"},r(d[5]).createElement(n,null)))}}]),u})(r(d[5]).Component),o=r(d[6]).StyleSheet.create({fill:{flex:1},textInputContainer:{flexDirection:'row',alignItems:'center',borderTopWidth:1,borderTopColor:'#eee',height:44},textInput:{flex:1,paddingLeft:10},text:{padding:10,color:'white'},textBubbleBackground:{backgroundColor:'#2f7bf6',borderRadius:20,width:110,margin:20}});e.title='<InputAccessoryView>',e.description='Example showing how to use an InputAccessoryView to build an iMessage-like sticky text input',e.examples=[{title:'Simple view with sticky input',render:function(){return r(d[5]).createElement(l,null)}}]},666706,[614,616,617,620,621,70,516]);