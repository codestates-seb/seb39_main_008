import { useEffect, useState } from 'react';
import { useForm } from 'react-hook-form';
import { useNavigate } from 'react-router-dom';
import BorderButton from '../components/common/BorderButton';
import ImageInput from '../components/common/ImageInput';
import ConfirmModal from './common/ConfirmModal';
import { Container, Mid, Bottom } from './WriteOrEditDiaryForm';
// import { addBook } from '../lib/axios';

const MakeorEditBookForm = (props) => {
  const [fileURL, setFileURL] = useState(null);
  const [confirm, setConfirm] = useState(false);

  const navigate = useNavigate();

  const {
    register,
    setValue,
    handleSubmit,
    formState: { errors },
  } = useForm({ defaultValues: { isOpen: 'true' } });

  const onFileChange = (imgURL) => {
    setFileURL(imgURL);
    console.log(fileURL);
    // setPreview(imgURL);
  };

  useEffect(() => {
    if (props.isEdit) {
      setValue('content', props.data.content);
    }
  }, []);
  const onUpdateBook = (data) => {
    console.log('update', data);
  };

  const onMakeBook = async (data) => {
    console.log('make', data);
    // const res = await addBook(data);
    // console.log(res);
  };

  const onCancle = () => {
    setConfirm(!confirm);
  };

  const confirmCancle = (res) => {
    if (res) {
      onCancle();
      navigate(-1);
    } else {
      onCancle();
    }
  };

  return (
    <Container
      onSubmit={
        props.isEdit ? handleSubmit(onUpdateBook) : handleSubmit(onMakeBook)
      }
    >
      <Mid>
        <div>
          <input
            wrap="on"
            className="title"
            type="text"
            placeholder="제목"
            defaultValue={props.data ? props.data.title : null}
            {...register('title', {
              required: true,
              validate: (value) => {
                if (value.trim() === '') return false;
              },
            })}
          />
          <input
            wrap="on"
            className="subtitle"
            id="subtitle"
            type="text"
            placeholder="소제목"
            defaultValue={props.data ? props.data.subtitle : null}
            {...register('subtitle', {
              required: true,
              validate: (value) => {
                if (value.trim() === '') return false;
              },
            })}
          />
          <div className="radioBox">
            <p>공개 여부</p>
            <label htmlFor="true">
              <input
                id="true"
                type="radio"
                value={'true'}
                {...register('isOpen')}
              />
              공개
            </label>
            <br />
            <label htmlFor="false">
              <input
                id="false"
                type="radio"
                value={'false'}
                {...register('isOpen')}
              />
              비공개
            </label>
          </div>
        </div>
        <ImageInput onFileChange={onFileChange} />
      </Mid>
      <Bottom>
        {props.isEdit && (
          <div className="preview">
            <p>수정 전 사진</p>
            <img
              className="previewImg"
              src={props.data.bookimage}
              alt="preview Img"
            />
          </div>
        )}

        <div className="rightbox">
          <p className="Error">
            {(errors.title || errors.subtitle) && '모든 내용을 입력해 주세요'}
          </p>
          <BorderButton
            width="120px"
            height="40px"
            type="submit"
            fontSize={`var(--fontSizeM)`}
            text={props.isEdit ? '수정하기' : '만들기'}
          />
          <BorderButton
            text="취소"
            width="120px"
            height="40px"
            fontSize={`var(--fontSizeM)`}
            onClick={() => {
              setConfirm(!confirm);
            }}
          />
        </div>
      </Bottom>
      {confirm && (
        <ConfirmModal
          message={'취소하시겠습니까?'}
          onComfirm={confirmCancle}
          target={'작성한 내용은 복구할 수 없습니다'}
        />
      )}
    </Container>
  );
};

export default MakeorEditBookForm;
