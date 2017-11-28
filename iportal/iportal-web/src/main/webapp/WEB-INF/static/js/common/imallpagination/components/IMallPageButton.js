import React, { Component, PropTypes } from 'react';
import classSet from 'classnames';

class IMallPageButton extends Component {

  constructor(props) {
    super(props);
  }
  pageBtnClick (e) {
    e.preventDefault();
    this.props.changePage(e.currentTarget.textContent);
  }
  render() {

    const classes = classSet({
      'active': this.props.active,
      'disabled': this.props.disable,
      'hidden': this.props.hidden,
      'page-item': true
    });

    let className = "everyPage";
    if(this.props.active){
      className="nowPage";
    }else if(this.props.isUpPage){
      className="upPage";
    }else if(this.props.isDownPage){
      className="downPage";
    }
    return (
        <li className={ classes }>
          <a href='#' onClick={ this.pageBtnClick.bind(this) } className={className}>{ this.props.children }</a>
        </li>
    );
  }
}
IMallPageButton.propTypes = {
  changePage: PropTypes.func,
  active: PropTypes.bool,
  disable: PropTypes.bool,
  hidden: PropTypes.bool,
  isUpPage: PropTypes.bool,
  isDownPage: PropTypes.bool,
  children: PropTypes.node
};

export default IMallPageButton;
