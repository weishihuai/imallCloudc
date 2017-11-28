/**
 * Created by HWJ on 2017/7/15.
 */
import React, {Component} from "react";


class TimeSelect extends Component {

    componentDidMount() {

        const {id,change} = this.props;
        $("#" + id).on("click", function (e) {
            e.stopPropagation();
            $(this).lqdatetimepicker({
                css: 'datetime-day',
                dateType: 'D',
                selectback: function () {
                    change(id,$("#"+id).val());
                }
            });
        });
    }

    render () {
        const {input, name, id, placeholder, className, required, type,readOnly} = this.props;
        return (
            <input name={name} id={id} placeholder={placeholder} className={className}  required={required}  type={type} readOnly={readOnly} {...input}/>
        );
    }
}

export default TimeSelect;
