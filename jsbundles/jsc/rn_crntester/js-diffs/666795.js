__d(function(g,r,i,a,m,e,d){'use strict';var t=20,o=(function(o){function l(o){var n;return r(d[1])(this,l),(n=r(d[2])(this,r(d[3])(l).call(this,o))).state={isUploading:!1,uploadProgress:null,randomPhoto:null,textParams:[]},n._isMounted=!0,n._fetchRandomPhoto=function(){r(d[4]).CameraRoll.getPhotos({first:t}).then(function(t){if(n._isMounted){var o=t.edges,l=o[Math.floor(Math.random()*o.length)],s=l&&l.node&&l.node.image;if(s){var u=s.width,h=s.height;u*=.25,h*=.25,r(d[4]).ImageEditor.cropImage(s.uri,{offset:{x:0,y:0},size:{width:u,height:h}},function(t){return n.setState({randomPhoto:{uri:t}})},function(t){})}}},function(t){})},n._addTextParam=function(){var t=n.state.textParams;t.push({name:'',value:''}),n.setState({textParams:t})},n._upload=function(){var t=new XMLHttpRequest;t.open('POST','http://posttestserver.com/post.php'),t.onload=function(){n.setState({isUploading:!1}),r(d[5]).handlePostTestServerUpload(t)};var o=new FormData;n.state.randomPhoto&&o.append('image',r(d[6])({},n.state.randomPhoto,{type:'image/jpg',name:'image.jpg'})),n.state.textParams.forEach(function(t){return o.append(t.name,t.value)}),t.upload.onprogress=function(t){t.lengthComputable&&n.setState({uploadProgress:t.loaded/t.total})},t.send(o),n.setState({isUploading:!0})},n._fetchRandomPhoto(),n}return r(d[0])(l,o),r(d[7])(l,[{key:"componentWillUnmount",value:function(){this._isMounted=!1}},{key:"_onTextParamNameChange",value:function(t,o){var n=this.state.textParams;n[t].name=o,this.setState({textParams:n})}},{key:"_onTextParamValueChange",value:function(t,o){var n=this.state.textParams;n[t].value=o,this.setState({textParams:n})}},{key:"render",value:function(){var t=this,o=null;this.state.randomPhoto&&(o=r(d[8]).createElement(r(d[4]).Image,{source:this.state.randomPhoto,style:n.randomPhoto}));var l=this.state.textParams.map(function(o,l){return r(d[8]).createElement(r(d[4]).View,{style:n.paramRow},r(d[8]).createElement(r(d[4]).TextInput,{autoCapitalize:"none",autoCorrect:!1,onChangeText:t._onTextParamNameChange.bind(t,l),placeholder:"name...",style:n.textInput}),r(d[8]).createElement(r(d[4]).Text,{style:n.equalSign},"="),r(d[8]).createElement(r(d[4]).TextInput,{autoCapitalize:"none",autoCorrect:!1,onChangeText:t._onTextParamValueChange.bind(t,l),placeholder:"value...",style:n.textInput}))}),s=this.state.isUploading?'Uploading...':'Upload',u=this.state.uploadProgress;null!==u&&(s+=' '+Math.round(100*u)+'%');var h=r(d[8]).createElement(r(d[4]).View,{style:n.uploadButtonBox},r(d[8]).createElement(r(d[4]).Text,{style:n.uploadButtonLabel},s));return this.state.isUploading||(h=r(d[8]).createElement(r(d[4]).TouchableHighlight,{onPress:this._upload},h)),r(d[8]).createElement(r(d[4]).View,null,r(d[8]).createElement(r(d[4]).View,{style:n.paramRow},r(d[8]).createElement(r(d[4]).Text,{style:n.photoLabel},"Random photo from your library (",r(d[8]).createElement(r(d[4]).Text,{style:n.textButton,onPress:this._fetchRandomPhoto},"update"),")"),o),l,r(d[8]).createElement(r(d[4]).View,null,r(d[8]).createElement(r(d[4]).Text,{style:[n.textButton,n.addTextParamButton],onPress:this._addTextParam},"Add a text param")),r(d[8]).createElement(r(d[4]).View,{style:n.uploadButton},h))}}]),l})(r(d[8]).Component),n=r(d[4]).StyleSheet.create({paramRow:{flexDirection:'row',paddingVertical:8,alignItems:'center',borderBottomWidth:r(d[4]).StyleSheet.hairlineWidth,borderBottomColor:'grey'},photoLabel:{flex:1},randomPhoto:{width:50,height:50},textButton:{color:'blue'},addTextParamButton:{marginTop:8},textInput:{flex:1,borderRadius:3,borderColor:'grey',borderWidth:1,height:50,paddingLeft:8},equalSign:{paddingHorizontal:4},uploadButton:{marginTop:16},uploadButtonBox:{flex:1,paddingVertical:12,alignItems:'center',backgroundColor:'blue',borderRadius:4},uploadButtonLabel:{color:'white',fontSize:16,fontWeight:'500'}});m.exports=o},666795,[614,616,617,620,516,666794,625,621,514]);