__d(function(g,r,i,a,m,e,d){'use strict';var t=(function(t){function n(t){var s;return r(d[1])(this,n),(s=r(d[2])(this,r(d[3])(n).call(this,t))).cancelled=!1,s.state={status:'',headers:'',contentSize:1,downloaded:0},s}return r(d[0])(n,t),r(d[4])(n,[{key:"download",value:function(){var t=this;this.xhr&&this.xhr.abort();var s=this.xhr||new XMLHttpRequest;s.onreadystatechange=function(){if(s.readyState===s.DONE){if(t.cancelled)return void(t.cancelled=!1);200===s.status?t.setState({status:'Download complete!',headers:s.getAllResponseHeaders()}):0!==s.status?t.setState({status:'Error: Server returned HTTP status of '+s.status+' '+s.responseText}):t.setState({status:'Error: '+s.responseText})}},s.open('GET','https://httpbin.org/response-headers?header1=value&header2=value1&header2=value2'),s.send(),this.xhr=s,this.setState({status:'Downloading...'})}},{key:"componentWillUnmount",value:function(){this.cancelled=!0,this.xhr&&this.xhr.abort()}},{key:"render",value:function(){var t='Downloading...'===this.state.status?r(d[5]).createElement(r(d[6]).View,{style:s.wrapper},r(d[5]).createElement(r(d[6]).View,{style:s.button},r(d[5]).createElement(r(d[6]).Text,null,"..."))):r(d[5]).createElement(r(d[6]).TouchableHighlight,{style:s.wrapper,onPress:this.download.bind(this)},r(d[5]).createElement(r(d[6]).View,{style:s.button},r(d[5]).createElement(r(d[6]).Text,null,"Get headers")));return r(d[5]).createElement(r(d[6]).View,null,t,r(d[5]).createElement(r(d[6]).Text,null,this.state.headers))}}]),n})(r(d[5]).Component),s=r(d[6]).StyleSheet.create({wrapper:{borderRadius:5,marginBottom:5},button:{backgroundColor:'#eeeeee',padding:8}});m.exports=t},666797,[614,616,617,620,621,514,516]);