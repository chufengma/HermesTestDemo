__d(function(g,r,i,a,m,e,d){'use strict';var t=(function(t){function l(){var t,n;r(d[1])(this,l);for(var o=arguments.length,c=new Array(o),s=0;s<o;s++)c[s]=arguments[s];return(n=r(d[2])(this,(t=r(d[3])(l)).call.apply(t,[this].concat(c)))).state={content:'Content will appear here'},n._setClipboardContent=function(){var t;return r(d[4]).async(function(l){for(;;)switch(l.prev=l.next){case 0:return r(d[5]).Clipboard.setString('Hello World'),l.prev=1,l.next=4,r(d[4]).awrap(r(d[5]).Clipboard.getString());case 4:t=l.sent,n.setState({content:t}),l.next=11;break;case 8:l.prev=8,l.t0=l.catch(1),n.setState({content:l.t0.message});case 11:case"end":return l.stop()}},null,null,[[1,8]])},n}return r(d[0])(l,t),r(d[6])(l,[{key:"render",value:function(){return r(d[7]).createElement(r(d[5]).View,null,r(d[7]).createElement(r(d[5]).Text,{onPress:this._setClipboardContent,style:n.label},"Tap to put \"Hello World\" in the clipboard"),r(d[7]).createElement(r(d[5]).Text,{style:n.content},this.state.content))}}]),l})(r(d[7]).Component),n=r(d[5]).StyleSheet.create({label:{color:'blue'},content:{color:'red',marginTop:20}});e.title='Clipboard',e.description='Show Clipboard contents.',e.examples=[{title:'Clipboard.setString() and getString()',render:function(){return r(d[7]).createElement(t,null)}}]},666767,[614,616,617,620,682,516,621,514]);